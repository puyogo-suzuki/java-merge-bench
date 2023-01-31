
public abstract class MergeSorterIntParallelBase {
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
    private void sortImpl(int v[], int out[], int l, int r) throws CloneNotSupportedException, InterruptedException {
	int m;
	if (l == r || l == (r-1)) return;
	m = (l + r) / 2;
	if(m - l > v.length / 4) {
	    Thread th = sortImplPara(out, v, l, m);
	    Thread th2 = sortImplPara(out, v, m, r);
	    th.join(); th2.join();
	} else {
	    sortImpl(out, v, l, m);
	    sortImpl(out, v, m, r);
	}
	merge(v, out, l, m, r);
    }
    private Thread sortImplPara(int v[], int out[], int l, int r) throws CloneNotSupportedException {
	Exec exe = new Exec(v,out,l,r);
	exe.start();
	return (Thread)exe;
    }
    public int[] sort(int value[]) {
	int out[] = new int[value.length];
	int tmp[] = new int[value.length];
	System.arraycopy(value, 0, out, 0, value.length);
	System.arraycopy(value, 0, tmp, 0, value.length);
	try{
	sortImpl(tmp, out, 0, value.length);
	} catch(Exception ex){
	    System.out.println(ex.toString());
	}
	return out;
    }

    public class Exec extends Thread {
	int v[]; int out[];
	int l, r;

	public Exec(int v[], int out[], int l, int r) {
	    this.v = v;
	    this.out = out;
	    this.l = l;
	    this.r = r;
	}
	
	public void run() {
	    try {
		sortImpl(v, out, l, r);
	    } catch(Exception ex) {
		System.out.println("FAIL\n");
	    }
	}
    }

}
