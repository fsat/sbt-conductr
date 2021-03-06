package impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import api.FooService;
import play.*;
import javax.inject.Inject;
import java.util.Date;
import java.io.*;

public class Module extends AbstractModule implements ServiceGuiceSupport {
	@Override
	protected void configure() {
		bindServices(serviceBinding(FooService.class, FooServiceImpl.class));
	}
}