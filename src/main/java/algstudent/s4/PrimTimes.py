import Helper 
import Prim
from time import time


n = 256
for casos in range(10):
    weights = Prim.makeMatrixBidirectional( Helper.triangularMatrixRandomIntegers(n, 100, 999) )

    t1 = time()
    primes = Prim.prim(weights)
    t2 = time()

    print("n =", n, "***", "time =", int(1000*(t2-t1)), "milliseconds)")
    n = n*2