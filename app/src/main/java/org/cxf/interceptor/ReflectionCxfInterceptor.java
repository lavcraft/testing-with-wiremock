package org.cxf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
    Optional.ofNullable(message.getContent(List.class))
        .ifPresent(list -> list.forEach(o -> {
              try {
                introspectObject(o);
              } catch (InvocationTargetException | IntrospectionException | IllegalAccessException ignored) {
              }
            })
        );
  }

  private void introspectObject(Object o) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
    log.info("{}", org.boon.Boon.toJson(o));
  }
}
