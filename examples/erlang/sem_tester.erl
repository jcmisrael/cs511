-module(sem_tester).
-export([start/0]).

start() ->
    Mutex = mp_semaphore:make(0),
    spawn(fun () -> q(Mutex) end),
    spawn(fun () -> p(Mutex) end).

q(Mutex) ->
    timer:sleep(500),
    io:format("A~n"),
    mp_semaphore:release(Mutex),
    timer:sleep(100),
    io:format("C~n").

p(Mutex) ->
    mp_semaphore:acquire(Mutex),
    io:format("B~n").
