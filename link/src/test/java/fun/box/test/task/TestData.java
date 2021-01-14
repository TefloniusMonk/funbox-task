package fun.box.test.task;

import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.dto.VisitView;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@UtilityClass
public class TestData {
    public VisitView newVisitView() {
        val view = new VisitView();
        view.setDomains(new HashSet<>(Arrays.asList("google.com", "yandex.ru")));
        view.setStatus("ok");
        return view;
    }

    public VisitForm newVisitForm() {
        val view = new VisitForm();
        view.setLinks(Arrays.asList("google.com", "yandex.ru"));
        return view;
    }

    public VisitForm newVisitForm2() {
        val view = new VisitForm();
        view.setLinks(Arrays.asList("any.other.domain.ru"));
        return view;
    }

    public VisitForm newVisitForm3() {
        val view = new VisitForm();
        view.setLinks(Arrays.asList(
                "https://ya.ru",
                "https://ya.ru?q=123",
                "funbox.ru",
                "https://stackoverflow.com/questions/1182827/how-to-exit-the-vim-editor"
        ));
        return view;
    }

    public VisitForm newVisitForm4() {
        val view = new VisitForm();
        view.setLinks(Arrays.asList(
                "ру.рф",
                "google.com"
        ));
        return view;
    }

    public List<String> validUrls() {
        return Arrays.asList(
                "http://foo.com/blah_blah",
                "http://foo.com/blah_blah/",
                "http://foo.com/blah_blah_(wikipedia)",
                "http://foo.com/blah_blah_(wikipedia)_(again)",
                "http://www.example.com/wpstyle/?p=364",
                "https://www.example.com/foo/?bar=baz&inga=42&quux",
                "http://df.ws/123",
                "http://userid:password@example.com:88",
                "http://userid:password@example.com:88/",
                "http://userid@example.com",
                "http://userid@example.com/",
                "http://userid@example.com:888",
                "http://userid@example.com:88/",
                "http://userid:password@example.com",
                "http://userid:password@example.com/",
                "http://142.42.1.1/",
                "http://142.42.1.1:88/",
                "http://foo.com/blah_(wikipedia)#cite-",
                "http://foo.com/blah_(wikipedia)_blah#cite-",
                "http://foo.com/(something)?after=parens",
                "http://code.google.com/events/#&product=browser",
                "http://j.mp",
                "ftp://foo.bar/baz",
                "http://1337.net",
                "http://a.b-c.de",
                "http://223.255.255.254",
                "https://ya.ru",
                "https://ya.ru?q=123",
                "funbox.ru",
                "фанбокс.рф",
                "https://stackoverflow.com/questions/1182827/how-to-exit-the-vim-editor"
        );
    }

    public List<String> invalidUrls() {
        return Arrays.asList(
                "http://",
                "http://.",
                "http://..",
                "http://../11",
                "http://?",
                "http://??",
                "http://??/11",
                "http://#11",
                "http://##11",
                "http://##/11",
                "http://foo.bar?q=Spaces should be encoded1",
                "//",
                "//a",
                "///a",
                "///",
                "rdar://12341",
                "h://test1",
                "http:// shouldfail.com1",
                ":// should fail",
                "http://foo.bar/foo(bar)baz quux1",
                "http://-error-.invalid/",
                "http://-a.b.co1",
                "http://a.b-.co1",
                "http://...11",
                "http://362812674811",
                "http://.www.foo.bar/11",
                "http://.www.foo.bar./11"
        );
    }
}
