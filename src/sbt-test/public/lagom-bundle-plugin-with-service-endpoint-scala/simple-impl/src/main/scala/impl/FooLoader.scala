package impl

import com.typesafe.conductr.bundlelib.lagom.scaladsl.ConductRApplicationComponents
import com.lightbend.lagom.internal.client.CircuitBreakerMetricsProviderImpl
import com.lightbend.lagom.internal.spi.CircuitBreakerMetricsProvider
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import api.FooService

class FooLoader extends LagomApplicationLoader {

	override def load(context: LagomApplicationContext): LagomApplication =
		new FooApplication(context) with ConductRApplicationComponents

	override def loadDevMode(context: LagomApplicationContext): LagomApplication =
		new FooApplication(context) with LagomDevModeComponents

	override def describeService = Some(readDescriptor[FooService])
}

abstract class FooApplication(context: LagomApplicationContext)
	extends LagomApplication(context)
		with AhcWSComponents {

	override lazy val lagomServer = LagomServer.forServices(
		bindService[FooService].to(new FooServiceImpl)
	)
}
