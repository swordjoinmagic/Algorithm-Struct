package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定N本图书和M种查询方法,
 *      求每次查询得到的所有图书(按id升序排列),
 *
 * 思路:
 *      1. map
 */
public class PAT1022DigitalLibrary_30point {

    public static void main(String[] args){
        PAT1022DigitalLibrary_30point pat1022 = new PAT1022DigitalLibrary_30point();
        pat1022.Input();
    }

    // 图书数量
    int N;

    // 查询数量
    int M;

    Map<String, TreeSet<String>> keywordMap = new HashMap<>();
    Map<String, TreeSet<String>> authorMap = new HashMap<>();
    Map<String, TreeSet<String>> publisherMap = new HashMap<>();
    Map<Integer, TreeSet<String>> yearMap = new HashMap<>();
    Map<String, TreeSet<String>> titleMap = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        for(int i=0;i<N;i++){
            int id = in.nextInt();
            in.nextLine();
            String title = in.nextLine();
            String author = in.nextLine();
            String[] keywords = in.nextLine().split(" ");
            String publisher = in.nextLine();
            int publishedYear = in.nextInt();

//            Book book = new Book(id,title,author,keywords,publisher, publishedYear);

            if(!titleMap.containsKey(title)) titleMap.put(title,new TreeSet<>());
            titleMap.get(title).add(String.valueOf(id));

            if(!authorMap.containsKey(author)) authorMap.put(author,new TreeSet<>());
            authorMap.get(author).add(String.valueOf(id));

            for(String keyword : keywords){
                if(!keywordMap.containsKey(keyword)) keywordMap.put(keyword,new TreeSet<>());
                keywordMap.get(keyword).add(String.valueOf(id));
            }

            if(!publisherMap.containsKey(publisher)) publisherMap.put(publisher,new TreeSet<>());
            publisherMap.get(publisher).add(String.valueOf(id));

            if(!yearMap.containsKey(publishedYear)) yearMap.put(publishedYear,new TreeSet<>());
            yearMap.get(publishedYear).add(String.valueOf(id));
        }
        Slove(in);
    }

    public void Slove(Scanner in){

        M = in.nextInt();
        in.nextLine();
        for(int i=0;i<M;i++){
            int queryID;
            String queryKey;

            String queryCommand = in.nextLine();
            queryID = queryCommand.charAt(0)-'0';
            queryKey = queryCommand.substring(3);

            System.out.println(queryCommand);
            switch (queryID){
                case 1:
                    // title
                    if(titleMap.containsKey(queryKey)){
                        for(String book : titleMap.get(queryKey)){
                            System.out.println(book);
                        }
                    }else {
                        System.out.println("Not Found");
                    }
                    break;
                case 2:
                    // author
                    if(authorMap.containsKey(queryKey)){
                        for(String book : authorMap.get(queryKey)){
                            System.out.println(book);
                        }
                    }else {
                        System.out.println("Not Found");
                    }
                    break;
                case 3:
                    // keyword
                    if(keywordMap.containsKey(queryKey)){
                        for(String book : keywordMap.get(queryKey)){
                            System.out.println(book);
                        }
                    }else {
                        System.out.println("Not Found");
                    }
                    break;
                case 4:
                    // publisher
                    if(publisherMap.containsKey(queryKey)){
                        for(String book : publisherMap.get(queryKey)){
                            System.out.println(book);
                        }
                    }else {
                        System.out.println("Not Found");
                    }
                    break;
                case 5:
                    int key = Integer.parseInt(queryKey);
                    // year
                    if(yearMap.containsKey(key)){
                        for(String book : yearMap.get(key)){
                            System.out.println(book);
                        }
                    }else {
                        System.out.println("Not Found");
                    }
                    break;
            }
        }
    }
}
