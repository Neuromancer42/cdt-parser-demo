extern __inline unsigned long long
__attribute__((__gnu_inline__, __always_inline__, __artificial__))
_mulx_u64 (unsigned long long __X, unsigned long long __Y,
    unsigned long long *__P)
{
  unsigned __int128 __res = (unsigned __int128) __X * __Y;
  *__P = (unsigned long long) (__res >> 64);
  return (unsigned long long) __res;
}

int main() {
    unsigned long long x = 10;
    unsigned long long y = 10;
    unsigned long long z;
    _mulx_u64(x, y, &z);
    return z;
}