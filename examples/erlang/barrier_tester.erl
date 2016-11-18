-module(barrier_tester).
-export([run/0,initialize/0]).

initialize() ->
    barrier:start(3),
    spawn(fun () -> p() end),
    spawn(fun () -> q() end),
    spawn(fun () -> r() end).

run() ->
    spawn(fun () -> p() end),
    spawn(fun () -> q() end),
    spawn(fun () -> r() end).


p() ->
    timer:sleep(500),
    io:format("P reached barrier~n"),
    barrier:reach_and_wait(coordinator),
    io:format("P past barrier~n").
q() ->
    timer:sleep(1500),
    io:format("Q reached barrier~n"),
    barrier:reach_and_wait(coordinator),
    io:format("Q past barrier~n").
r() ->
    timer:sleep(2500),
    io:format("R reached barrier~n"),
    barrier:reach_and_wait(coordinator),
    io:format("R past barrier~n").
