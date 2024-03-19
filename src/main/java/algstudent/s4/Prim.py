import Helper 
from math import inf

def makeMatrixBidirectional(m):
    size = len(m)

    for i in range(size):
        for j in range(size):
            if i != j and m[i][j] == 0:
                m[i][j] = m[j][i]
    
    return m

def minW(w, v):
    min = inf
    pos = -1
    for i in range(len(w)):
        if w[i] < min and not v[i]:
            min = w[i]
            pos = i

    return min, pos

def prim(weights):
    size = len(weights)
    visited = [False] * size
    p = []

    visited[0] = True
    w = 0
    for ct in range(1, size):
        min_weight = inf
        f_n = t_n = -1

        for i in range(size):
            if visited[i]:
                m_w, t = minW(weights[i], visited)
                if m_w < min_weight:
                    min_weight = m_w
                    f_n = i
                    t_n = t

        visited[t_n] = True
        p.append(f"{f_n}-{t_n}")
        w += min_weight

    return p, w

# path = "graph4.txt"
# path = "graph8.txt"
# path = "graph16.txt"
# path = "graph64.txt"
# path = "graph128.txt"
# path = "graph256.txt"

# weights = makeMatrixBidirectional( Helper.triangularMatrixFromFile(path) )

# print(prim(weights))