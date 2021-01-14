package fun.box.test.task.db;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Visit implements Serializable {
    private static final long serialVersionUID = 345345352342L;
    private String timestamp;
    private List<String> links;
}
