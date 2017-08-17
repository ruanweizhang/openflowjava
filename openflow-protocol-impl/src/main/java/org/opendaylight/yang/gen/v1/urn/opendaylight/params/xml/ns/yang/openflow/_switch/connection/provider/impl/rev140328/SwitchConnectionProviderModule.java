/**
* Generated file

* Generated from: yang module name: openflow-switch-connection-provider-impl  yang module local name: openflow-switch-connection-provider-impl
* Generated by: org.opendaylight.controller.config.yangjmxgenerator.plugin.JMXGenerator
* Generated at: Fri Mar 28 17:50:58 PDT 2014
*
* Do not modify this file unless it is present under src/main directory
*/
package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflow._switch.connection.provider.impl.rev140328;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;
import java.lang.reflect.Method;
import org.opendaylight.controller.config.api.osgi.WaitingServiceTracker;
import org.opendaylight.openflowjava.protocol.spi.connection.SwitchConnectionProvider;
import org.osgi.framework.BundleContext;

/**
 * @deprecated Replaced by blueprint wiring
 */
@Deprecated
public final class SwitchConnectionProviderModule extends AbstractSwitchConnectionProviderModule {
    private BundleContext bundleContext;

    /**
     * @param identifier
     * @param dependencyResolver
     */
    public SwitchConnectionProviderModule(final org.opendaylight.controller.config.api.ModuleIdentifier identifier, final org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    /**
     * @param identifier
     * @param dependencyResolver
     * @param oldModule
     * @param oldInstance
     */
    public SwitchConnectionProviderModule(final org.opendaylight.controller.config.api.ModuleIdentifier identifier, final org.opendaylight.controller.config.api.DependencyResolver dependencyResolver,
            final SwitchConnectionProviderModule oldModule, final java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public AutoCloseable createInstance() {
        // The service is provided via blueprint so wait for and return it here for backwards compatibility.
        String typeFilter = String.format("(type=%s)", getIdentifier().getInstanceName());
        final WaitingServiceTracker<SwitchConnectionProvider> tracker = WaitingServiceTracker.create(
                SwitchConnectionProvider.class, bundleContext, typeFilter);
        final SwitchConnectionProvider actualService = tracker.waitForService(WaitingServiceTracker.FIVE_MINUTES);

        // We don't want to call close on the actual service as its life cycle is controlled by blueprint but
        // we do want to close the tracker so create a proxy to override close appropriately.
        return Reflection.newProxy(SwitchConnectionProvider.class, new AbstractInvocationHandler() {
            @Override
            protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("close")) {
                    tracker.close();
                    return null;
                } else {
                    return method.invoke(actualService, args);
                }
            }
        });
    }

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    @Override
    public boolean canReuseInstance(AbstractSwitchConnectionProviderModule oldModule) {
        return true;
    }
}