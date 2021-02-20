import java.util.List;

public class Maps {
    private String firstLink;
    private List<String> secondLink;


    public String getFirstLink() {
        return firstLink;
    }

    public void setFirstLink(String firstLink) {
        this.firstLink = firstLink;
    }

    public List<String> getSecondLink() {
        return secondLink;
    }

    public void setSecondLink(List<String> secondLink) {
        this.secondLink = secondLink;
    }
    public void addSecondLink(List<String> list,String link){
        list.add(link);
    }
}
