public class MergeSorterIntParallel extends MergeSorterIntParallelBase {
    @Override
    public boolean Compare(int x, int y) {
	return x <= y;
    }
}
