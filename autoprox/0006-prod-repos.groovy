
import org.commonjava.aprox.autoprox.data.*;

import java.net.MalformedURLException;

import org.commonjava.aprox.model.core.*;

class ProductRepositoryRule extends AbstractAutoProxRule
{
    boolean matches( String name ){
        // format: product+<prod-name>+<prod-ver>
        name.startsWith( "product+" ) && name.split('\\+').length == 3
    }

    HostedRepository createHostedRepository( String named )
    {
        HostedRepository r = new HostedRepository( named )
        r.setAllowSnapshots( true )
        r.setAllowReleases( true )

        r
    }

    Group createGroup( String named )
    {
        def match = named.split('\\+')
        //def productPrefix = match[0]

        def prodName = match[1]
        def prodVer = match[2]

        Group g = new Group( named );
        g.addConstituent( new StoreKey( StoreType.remote, named ) )
        g.addConstituent( new StoreKey( StoreType.hosted, named ) )

        // TODO: For demo only, we should probably control this more closely in the product space.
        g.addConstituent( new StoreKey( StoreType.group, 'public' ) )
        
        g
    }

//    RemoteRepository createRemoteRepository( String name )
//        throws AutoProxRuleException, MalformedURLException
//    {
//        new RemoteRepository( name, 'http://localhost/api/group/public' );
//    }
}

