import java.io.IOException;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static final int LEN = 2*1024*1024;
    public static void main(String args[]) {
	MergeSorterInt mergeInt = new MergeSorterInt();
	MergeSorterIntParallel mergePara = new MergeSorterIntParallel();
	MergeSorter merge = new MergeSorter();
	long startTime, endTime;
	int input[] = new int[LEN];

	try(var reader = new FileInputStream("./randdata")) {
		byte buf[] = new byte[LEN*4];
		reader.read(buf);
		ByteBuffer.wrap(buf).asIntBuffer().get(input, 0, LEN);
	} catch (Exception ex) {
	    System.out.println(ex);
	    return;
	}
	System.out.println("iterate, base, threaded, generics");
	for(int co = 0; co < 100; ++co) {
            int a, b, c;
            System.out.print(co + ", ");
	    {
		startTime = System.nanoTime();
		int result[] = mergeInt.sort(input);
		endTime = System.nanoTime();
                a = result[0];
		System.out.print((endTime - startTime) + ", ");
	    }
	    {
		startTime = System.nanoTime();
		int result[] = mergePara.sort(input);
		endTime = System.nanoTime();
                b = result[0];
		System.out.print((endTime - startTime) + ", ");
	    }
	    {
		Integer input2[] = IntStream.of(input).boxed().toArray(Integer[]::new);
		startTime = System.nanoTime();
		Integer out[] = new Integer[input.length];
		{
		    Integer tmp[] = new Integer[input.length];
		    merge.sort(input2, out, tmp);
		}
		endTime = System.nanoTime();
                c = out[0];
		System.out.println(endTime - startTime);
	    }
            if(a != b || a != c) {
                System.out.println("FAIL\n");
                return;
            }
	    System.gc();
	}
    }
}
