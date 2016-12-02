active proctype P() {
  byte N = 10;
  byte sum = 0;

  byte i;
  i = 1;

  do
    :: i > N -> break
    :: else ->
       sum = sum + i;
       i++
  od;
  printf("The sum of the first %d numbers = %d\n", N, sum);
}