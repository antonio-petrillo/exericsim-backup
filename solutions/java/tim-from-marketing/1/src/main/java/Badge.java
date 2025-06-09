import java.lang.StringBuilder;

public class Badge {
    public String print(Integer id, String name, String department) {
        var sb = new StringBuilder();
        if (id != null) {
            sb.append("["+ id +"]");
        }
        if (name != null) {
            if(sb.length() > 0) {
                sb.append(" - ");
            }
            sb.append(name);
        }
        if(sb.length() > 0) {
                sb.append(" - ");
        }

        sb.append(department != null ? department.toUpperCase() : "OWNER");
        return sb.toString();
    }
}
