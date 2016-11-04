ERLSOURCE = interp.erl env.erl tests.erl
LEXERSOURCE = lexer.xrl
PARSERSOURCE = parser.yrl
TYPESSOURCE = types.hrl
COMPILEDSOURCES = $(PARSERSOURCE:.yrl=.erl) $(LEXERSOURCE:.xrl=.erl)
OUTPUTTAR = Jake.Israel.cs511.Assignment3.tgz
OUTPUTZIP = Jake.Israel.cs511.Assignment3.zip

all: clean compile run

clean:
	-rm -f $(COMPILEDSOURCES)
	-rm -f *.beam
	-rm -f erl_crash.dump

compile:
	erlc $(ERLSOURCE) $(COMPILEDSOURCES)

run:

tar:
	tar -cvzf $(OUTPUTTAR) $(ERLSOURCE) $(LEXERSOURCE) $(PARSERSOURCE) $(TYPESSOURCE) Makefile

zip:
	zip $(OUTPUTZIP) $(ERLSOURCE) $(LEXERSOURCE) $(PARSERSOURCE) $(TYPESSOURCE) Makefile
