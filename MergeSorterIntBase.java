public abstract class MergeSorterIntBase {
    public abstract boolean Compare(int x, int y);
    private void merge(int v[], int out[], int l, int m, int r) {
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
    private void sortImpl(int v[], int out[], int l, int r) {
	int m;
	if (l == r || l == (r-1)) return;
	m = (l + r) / 2;
	sortImpl(out, v, l, m);
	sortImpl(out, v, m, r);
	merge(v, out, l, m, r);
    }
    public int[] sort(int value[]) {
	int out[] = new int[value.length];
	int tmp[] = new int[value.length];
	System.arraycopy(value, 0, out, 0, value.length);
	System.arraycopy(value, 0, tmp, 0, value.length);
	sortImpl(tmp, out, 0, value.length);
	return out;
    }
}
/*
public abstract class MergeSorterIntBase {
    public abstract boolean Compare(int x, int y);
    private void merge(int v[], int tmp[], int l, int m, int r) {
	int i = l;
	int j = m;
	int k = l;
	while(i < m &&  j < r) {
	    if (Compare(v[i], v[j]))  tmp[k++] = v[i++];
	    else                      tmp[k++] = v[j++];
	}
	if(i == m) System.arraycopy(v, j, tmp, k, r-j);
	else if(j == r) System.arraycopy(v, i, tmp, k, m-i);
	System.arraycopy(tmp, l, v, l, r-l);
    }
    private void sortImpl(int v[], int out[], int l, int r) {
	int m;
	if (l == r || l == (r-1)) return;
	m = (l + r) / 2;
	sortImpl(v, out, l, m);
	sortImpl(v, out, m, r);
	merge(v, out, l, m, r);
    }
    public int[] sort(int value[]) {
	int out[] = new int[value.length];
	int tmp[] = new int[value.length];
	System.arraycopy(value, 0, out, 0, value.length);
	sortImpl(out, tmp, 0, value.length);
	return out;
    }
}*/
