package org.cxf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * @author tolkv
 * @since 15/08/15
 */
@Slf4j
public class ReflectionCxfInterceptor extends AbstractPhaseInterceptor {
  public ReflectionCxfInterceptor() {
    this(Phase.SETUP);
  }

  public ReflectionCxfInterceptor(String phase) {
    super(phase);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void handleMessage(Message message) throws Fault {
    List<Object> messagePartList = message.getContent(List.class);

    Optional.ofNullable(messagePartList)
        .ifPresent(list -> list.forEach(o -> {
              try {
                BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                  if (property.getName().equals("class")) continue;
                  Method method = property.getReadMethod();
                  Object result = method.invoke(o);
                  log.info("{}:{}", property.getName(), result);
                }
              } catch (InvocationTargetException | IntrospectionException | IllegalAccessException ignored) {
              }
            })

        );
  }
}
