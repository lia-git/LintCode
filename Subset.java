递归搜索模板 / DFS / Backtracking   
虽然代码注释已经较为详细，但是还是建议参照文档一起阅读
O(nlogn)

注意：用startIndex来track到哪一步。

/*
Given a set of distinct integers, return all possible subsets.

Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Note
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Challenge
Can you do it in both recursively and iteratively?

Tags Expand 
Recursion Facebook Uber
*/

// 递归：实现方式，一种实现DFS算法的一种方式
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        if (nums == null) {
            return results;
        }
        
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        helper(new ArrayList<Integer>(), nums, 0, results);
        return results;
    }
    
    
    // 递归三要素
    // 1. 递归的定义：在 Nums 中找到所有以 subset 开头的的集合，并放到 results
    private void helper(ArrayList<Integer> subset,
                        int[] nums,
                        int startIndex,
                        ArrayList<ArrayList<Integer>> results) {
        // 2. 递归的拆解
        // deep copy
        // results.add(new ArrayList<>(subset)); 记住这里要new一个List空间用来存储结果才行
        // 这里是遍历所有的子集，所以无需判断条件
        // 其他条件下，需要判断遍历得到的答案是否满足条件，满足的话将其add到resuts中并且 return
        results.add(new ArrayList<Integer>(subset));
        
        //  i表示当前loop要取的元素的下标，startIndex表示从该元素开始取.
        //  有些题目中可能会限制i与starIndex的关系.
        for (int i = startIndex; i < nums.length; i++) {
            // [1] -> [1,2]
            // 其他题目中可能存在不符合条件的情况，需要加上一个if语句来判断是否要add到subset中
            subset.add(nums[i]);
            // 寻找所有以 [1,2] 开头的集合，并扔到 results
            // 注意这里递归传入的是i + 1表示startIndex从下一个位置开始
            helper(subset, nums, i + 1, results);
            // [1,2] -> [1]  回溯
            subset.remove(subset.size() - 1);
        }
        
        // 3. 递归的出口
    }
}

// Non Recursion
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int n = nums.length;
        Arrays.sort(nums);
        
        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> subset = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}