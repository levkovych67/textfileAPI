public class Validator {

    private String q;
    private String length;
    private String limit;

    public Validator(String q, String length, String limit) {
        this.q = q;
        this.length = length;
        this.limit = limit;
    }

    public String validateQ() {
        if (this.q == null || this.q.isEmpty()) {
            return "";
        } else {
            return this.q;
        }
    }

    public Integer validateLength() {
        Integer length = Integer.MAX_VALUE;
        try {
            length = Integer.valueOf(this.length);
        } catch (NumberFormatException e) {
            return length;
        }
        return length;
    }

    public Integer validateLimit(){
        Integer limit = 10000;
        try {
            limit = Integer.valueOf(this.limit);
        } catch (NumberFormatException e) {
            return limit;
        }
        return limit;
    }


}
