package Project;

public class WordEntity implements Comparable<WordEntity>{
    @Override
    public int compareTo(WordEntity o) {
        int cmp = count.intValue() - o.count.intValue();
        return (cmp == 0 ? key.compareTo(o.key) : -cmp);
        // -cmp descending order，cmp ascending order
        //TreeSet will call WorkForMap's compareTo() to sort
    }

    private String key;
    private Integer count;

    public WordEntity ( String key,Integer count) {
        this.key = key;
        this.count = count;
    }

    public WordEntity(){

    }

    @Override
    public String toString() {
        return key + " The occurrence time is：" + count;
    }

    public String getKey() {
        return key;
    }

    public Integer getCount() {
        return count;
    }
}
