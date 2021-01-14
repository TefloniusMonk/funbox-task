package fun.box.test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitView extends SimpleResponse {
    private Set<String> domains;

    public static VisitView of(Set<String> domain){
        return new VisitView(domain);
    }

    public VisitView withStatusOk(){
        status = OK;
        return this;
    }
}
