-module(sensor).
-export([loop/1]).
-author("Jake Israel").

loop(From) ->
    Sleep_time = rand:uniform(10000),
    Measurement = rand:uniform(11),
    if
        Measurement == 11 ->
            exit({anomalous_reading});
        true ->
            From ! {self(), Measurement}
    end,
    timer:sleep(Sleep_time),
    From ! {self(), Measurement},
    loop(From).
