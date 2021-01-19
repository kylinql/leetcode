package cn.kylin.leetcode.并查集;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: kylin
 * @Date: 2021/1/18 下午5:58
 */
public class 账户合并_721 {

    /**
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * <p>
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     * <p>
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
     * 输出：
     * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     * 解释：
     * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
     * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
     * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
     * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     *  
     * <p>
     * 提示：
     * <p>
     * accounts的长度将在[1，1000]的范围内。
     * accounts[i]的长度将在[1，10]的范围内。
     * accounts[i][j]的长度将在[1，30]的范围内。
     */


    /**
     * 官方题解 并查集+HashMap
     *
     * @param args
     */

    public static void main(String[] args) {

        String str = "[[\"Gabe\",\"Gabe0@m.co\",\"Gabe3@m.co\",\"Gabe1@m.co\"],[\"Kevin\",\"Kevin3@m.co\",\"Kevin5@m.co\",\"Kevin0@m.co\"],[\"Ethan\",\"Ethan5@m.co\",\"Ethan4@m.co\",\"Ethan0@m.co\"],[\"Hanzo\",\"Hanzo3@m.co\",\"Hanzo1@m.co\",\"Hanzo0@m.co\"],[\"Fern\",\"Fern5@m.co\",\"Fern1@m.co\",\"Fern0@m.co\"]]";
//        str = "[[\"David\",\"David0@m.co\",\"David1@m.co\"],[\"David\",\"David3@m.co\",\"David4@m.co\"],[\"David\",\"David4@m.co\",\"David5@m.co\"],[\"David\",\"David2@m.co\",\"David3@m.co\"],[\"David\",\"David1@m.co\",\"David2@m.co\"]]";
        List<List> input = JSONObject.parseArray(str, List.class);
        List<List<String>> accounts = new ArrayList<>();
        for (List l : input) {
            List<String> list = (List<String>) l.stream().map(Object::toString).collect(Collectors.toList());
            accounts.add(list);
        }
        System.out.println(accountsMerge_1(accounts));
    }


    /**
     * @param accounts
     * @return
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, List<String>> accountMap = new HashMap<>();
        Map<String, Integer> emailIdMap = new HashMap<>();
        Map<Integer, String> idNameMap = new HashMap<>();
        Integer[] userId = {0};
        accounts.forEach(account -> {
            // 读取每个账户，为账户寻找是否存在，并且合并
            Set<Integer> ownerIdSet = new HashSet<>();
            List<String> emailList = new ArrayList<>();
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                if (emailIdMap.containsKey(email)) {
                    // 已存在 绑定用户
                    ownerIdSet.add(emailIdMap.get(email));
                }
                emailList.add(email);
            }

            if (ownerIdSet.isEmpty()) {
                // 首次出现的用户
                Integer finalId = ++userId[0];
                emailList.parallelStream().forEach(email -> emailIdMap.put(email, finalId));
                idNameMap.put(finalId, account.get(0));
                return;
            }

            if (ownerIdSet.size() > 1) {
                // 用户数据合并
                Integer finalId = ownerIdSet.iterator().next();
                emailIdMap.entrySet().stream().filter(entry -> {
                    if (ownerIdSet.contains(entry.getValue())) {
                        return true;
                    }
                    return false;
                }).forEach(entry -> emailIdMap.put(entry.getKey(), finalId));

                emailList.parallelStream().forEach(email -> emailIdMap.put(email, finalId));
            } else {
                Integer finalId = ownerIdSet.iterator().next();
                emailList.parallelStream().forEach(email -> emailIdMap.put(email, finalId));
            }
        });

        emailIdMap.forEach((email, id) -> {
            List<String> emailLis = accountMap.getOrDefault(id, new ArrayList<>());
            emailLis.add(email);
            // 按照字母序列排序
            accountMap.put(id, emailLis);
        });

        return accountMap.entrySet().stream().map(entry -> {
            String name = idNameMap.get(entry.getKey());
            List<String> emailList = entry.getValue();
            emailList.sort(String::compareTo);
            emailList.add(0, name);
            return emailList;
        }).collect(Collectors.toList());
    }

    public static List<List<String>> accountsMerge_1(List<List<String>> accounts) {
        Map<String, Integer> emailIdMap = new HashMap<>();
        Map<Integer, String> idNameMap = new HashMap<>(64);
        Integer[] idFather = new Integer[1005];  // 编号和编号对应的父亲id
        Integer userId = 0;
        for (List<String> account : accounts) {
            Integer thisId = ++userId;
            Set<Integer> childIdSet = new HashSet<>();
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                if (emailIdMap.containsKey(email)) {
                    // 获取这个email对应的拥有者
                    Integer id = emailIdMap.get(email);
                    childIdSet.add(id);
                }
                emailIdMap.put(email, thisId);
            }

            // 找到了这个节点的所有有关联的节点
            idFather[thisId] = thisId;
            // 当前节点是所有节点的父亲节点
            for (Integer childId : childIdSet) {
                changeFather(childId, thisId, idFather);
            }

            idNameMap.put(thisId, account.get(0));
        }

        Map<Integer, List<String>> accountMap = new HashMap<>();
        emailIdMap.entrySet().parallelStream().forEach(entry -> {
            String email = entry.getKey();
            Integer id = entry.getValue();
            Integer father = getFather(id, idFather);
            List<String> emails = accountMap.getOrDefault(father, new ArrayList<>());
            emails.add(email);
            accountMap.put(father, emails);
        });


        return accountMap.entrySet().stream().map(entry -> {
            String name = idNameMap.get(entry.getKey());
            entry.getValue().toArray(new String[0]);
            List<String> emailList = entry.getValue();
            emailList.sort(String::compareTo);
            emailList.add(0, name);
            return emailList;
        }).collect(Collectors.toList());
    }

    private static void changeFather(Integer childId, Integer fatherId, Integer[] idFather) {
        if (idFather[childId].equals(childId)) {
            idFather[childId] = fatherId;
            return;
        }
        changeFather(idFather[childId], fatherId, idFather);
        idFather[childId] = fatherId;
        return;
    }

    private static Integer getFather(Integer id, Integer[] idFather) {
        if (idFather.length > id && !idFather[id].equals(id)) {
            return getFather(idFather[id], idFather);
        }
        return id;
    }
}
