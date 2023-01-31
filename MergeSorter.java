public class MergeSorter extends MergeSorterBase<Integer> {
    @Override
    public boolean Compare(Integer x, Integer y) {
	return x <= y;
    }
}
