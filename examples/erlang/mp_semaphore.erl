-module(mp_semaphore).
-export([make/1,acquire/1,release/1]).

make(Permission) ->
    spawn(fun () -> loop(Permission) end).

loop(Permission) when Permission > 0 ->
    loop_acquire_release(Permission);
loop(_Permission) ->
    loop_release().

loop_release() ->
    receive
        {release} ->
            loop_acquire_release(1)
    end.

loop_acquire_release(Permission) ->
    receive
        {release} ->
            loop_acquire_release(Permission + 1);
        {acquire, From, Ref} when Permission == 1 ->
            From ! {ok, Ref},
            loop_release();
        {acquire, From, Ref} ->
            From ! {ok, Ref},
            loop_acquire_release(Permission - 1)
    end.

acquire(Mutex) ->
    Ref = make_ref(),
    Mutex ! {acquire, self(), Ref},
    receive
        {ok, Ref} ->
            ok
    end.

release(Mutex) ->
    Mutex ! {release}.
