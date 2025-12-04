
void main() {

    int[] nums = new int[] {21, 4, 6, 39, 3, 19, 12};

    int temp = nums[0];
    nums[0] = nums[1];
    nums[1] = temp;

    IO.println(Arrays.toString(nums));
}


