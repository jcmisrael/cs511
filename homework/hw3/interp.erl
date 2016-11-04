-module(interp).
-export([scanAndParse/1,runFile/1,runStr/1]).
-include("types.hrl").

loop(InFile,Acc) ->
    case io:request(InFile,{get_until,prompt,lexer,token,[1]}) of
        {ok,Token,_EndLine} ->
            loop(InFile,Acc ++ [Token]);
        {error,token} ->
            exit(scanning_error);
        {eof,_} ->
            Acc
    end.

scanAndParse(FileName) ->
    {ok, InFile} = file:open(FileName, [read]),
    Acc = loop(InFile,[]),
    file:close(InFile),
    {Result, AST} = parser:parse(Acc),
    case Result of 
	ok -> AST;
	_ -> io:format("Parse error~n")
    end.


-spec runFile(string()) -> valType().
runFile(FileName) ->
    valueOf(scanAndParse(FileName),env:new()).

scanAndParseString(String) ->
    {_ResultL, TKs, _L} = lexer:string(String),
    parser:parse(TKs).

-spec runStr(string()) -> valType().
runStr(String) ->
    {Result, AST} = scanAndParseString(String),
    case Result  of 
    	ok -> valueOf(AST,env:new());
    	_ -> io:format("Parse error~n")
    end.


-spec numVal2Num(numValType()) -> integer().
numVal2Num({num, N}) ->
    N.

-spec boolVal2Bool(boolValType()) -> boolean().
boolVal2Bool({bool, B}) ->
    B.

-spec valueOf(expType(),envType()) -> valType().
% NUM EXPRESSION %
valueOf({num, N}, _Env) ->
    {num, N};
valueOf({bool, B}, _Env) ->
    {bool, B};
valueOf({numExp, {num, _, N}}, _Env) ->
    {num, N};
% ID EXP %
valueOf({idExp, {id, _, ID}}, Env) ->
    env:lookup(Env, ID);
% DIFF EXPRESSION %
valueOf({diffExp, Exp1, Exp2}, Env) ->
    {num, numVal2Num(valueOf(Exp1, Env)) -
         numVal2Num(valueOf(Exp2, Env))};
% PLUS EXPRESSION %
valueOf({plusExp, Exp1, Exp2}, Env) ->
    {num, numVal2Num(valueOf(Exp1, Env)) +
         numVal2Num(valueOf(Exp2, Env))};
% isZeroExp EXPRESSION %
valueOf({isZeroExp, Exp}, Env) ->
    BoolVal = numVal2Num(valueOf(Exp, Env)) == 0,
    {bool, BoolVal};
% IF THEN ELSE EXP %
valueOf({ifThenElseExp, true, Exp2, _Exp3}, Env) ->
    valueOf(Exp2, Env);
valueOf({ifThenElseExp, false, _Exp2, Exp3}, Env) ->
    valueOf(Exp3, Env);
valueOf({ifThenElseExp, Exp1, Exp2, Exp3}, Env) ->
    valueOf({ifThenElseExp, boolVal2Bool(valueOf(Exp1, Env)), Exp2, Exp3}, Env);
% LET EXPRESSIONS %
valueOf({letExp, {id, _, Id}, ValExp, InExp}, Env) ->
    valueOf(InExp, env:add(Env, Id, valueOf(ValExp, Env)));
% PROC EXPRESSION %
valueOf({procExp, {id, _, ID}, Exp}, Env) ->
    {proc, ID, Exp, Env};
% APP EXPRESSION %
valueOf({appExp, ProcID, ValExp}, Env) ->
    {proc, ProcExpID, ProcExp, ProcEnv} = valueOf(ProcID, Env),
    valueOf({letExp, {id, 1, ProcExpID}, valueOf(ValExp, Env), ProcExp}, ProcEnv).




    %% complete
