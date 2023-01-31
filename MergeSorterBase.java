public abstract class MergeSorterBase<T> {
    public abstract boolean Compare(T x, T y);
    private void merge(T v[], T out[], int l, int m, int r) {
	int i = l;
	int j = m;
	int k = l;
	while(i < m &&  j < r) {
	    if (Compare(v[i], v[j])) out[k++] = v[i++];
	    else                     out[k++] = v[j++];
	}
	if(i == m) System.arraycopy(v, j, out, k, r-j);
	else if(j == r) System.arraycopy(v, i, out, k, m-i);
    }
    private void sortImpl(T v[], T out[], int l, int r) {
	int m;
	if (l == r || l == (r-1)) return;
	m = (l + r) / 2;
	sortImpl(out, v, l, m);
	sortImpl(out, v, m, r);
	merge(v, out, l, m, r);
    }
    public void sort(T value[], T out[], T tmp[]) {
	System.arraycopy(value, 0, out, 0, value.length);
	System.arraycopy(value, 0, tmp, 0, value.length);
	sortImpl(tmp, out, 0, value.length);
    }
}
