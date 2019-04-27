package utils;

import net.sf.cglib.beans.BeanCopier;

import java.util.List;

public class MapperUtils {

    public static void beanCopy(Object source, Object target) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopier.copy(source, target, null);
    }

    public static void batchBeanCopy(List sources, List targets) {
        if (sources.isEmpty()) {
            return;
        }
        BeanCopier beanCopier = BeanCopier.create(sources.get(0).getClass(), targets.get(0).getClass(), false);
        for (int i = 0; i < sources.size(); i++) {
            beanCopier.copy(sources.get(i), targets.get(i), null);
        }
    }
}
