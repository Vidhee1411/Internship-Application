public class ApplicationDriver {
    public static void main(String[] args){
        SearchableDatabase a = SearchableDatabase.getInstance();
        a = DataLoader.loadData();
    }
}
