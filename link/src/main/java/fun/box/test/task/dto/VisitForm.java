package fun.box.test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitForm {
    private List<String> links;

    public static VisitForm of(List<String> links){
        return new VisitForm(links);
    }
}
