-module(factorial_server).
-compile(export_all).

myproc() ->
    timer:sleep(reason).

chain(0) ->
    receive
        _ ->
            ok
    after 2000 ->
            exit("chain dies here")
    end;
chain(N) ->
    Pid = spawn(fun() -> chain(N-1) end),
    link(Pid),
    receive
        _ ->
            ok
    end.

start() ->
    spawn(?MODULE, restarter, []).

factorial_server() ->
    receive
        {From, N} ->
            self() ! {From, N, 0};
        {From, N, 0} ->
            self() ! {From, N-1, N};
        {From, 0, N} ->
            From ! {self(), N}
        end,
    factorial_server().

restarter() ->
    process_flag(trap_exit, true),
    Pid = spawn_link(?MODULE, factorial_server, []),
    io:format("Restarted fs~n~p", [Pid]),
    register(factorial_server, Pid),
    receive
        {'EXIT', Pid, normal} ->
            ok;
        {'EXIT', Pid, shutdown} ->
            ok;
        {'EXIT', Pid, _} ->
            restarter()
    end.

