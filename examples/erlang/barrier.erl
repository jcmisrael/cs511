-module(barrier).
-export([start/1,reach_and_wait/1]).

start(N) ->
    PID = spawn(fun () -> coordinate(N, N, []) end),
    register(coordinator, PID).

coordinate(N, X, List) when X > 0->
    receive
        {reached, PID, Ref} ->
            coordinate(N , X - 1, List ++ [{PID, Ref}])
    end;
coordinate(N, 0, List) ->
    [ From ! {all_reached, Ref} || {From, Ref} <- List],
    coordinate(N, 0, List).

reach_and_wait(N) ->
    Ref = make_ref(),
    N ! {reached, self(), Ref},
    receive
        {all_reached, Ref} ->
            ok
    end.
