public class MergeSorterInt extends MergeSorterIntBase {
    @Override
    public boolean Compare(int x, int y) {
	return x <= y;
    }
}
