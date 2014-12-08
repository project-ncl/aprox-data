
import org.commonjava.aprox.autoprox.data.*;

import java.net.MalformedURLException;

import org.commonjava.aprox.model.*;

class BuildRepositoryRule extends AbstractAutoProxRule
{
    boolean matches( String name ){
        // format: build+<prod-name>+<prod-ver>+<proj-name>+<number>
        name.startsWith( "build+" ) && name.split('\\+').length == 5
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
        //def buildPrefix = match[0]

        def prodName = match[1]
        def prodVer = match[2]
        def projName = match[3]
        def projBuild = match[4]

        Group g = new Group( named );
        g.addConstituent( new StoreKey( StoreType.group, "product+${prodName}+${prodVer}" ) )
        g.addConstituent( new StoreKey( StoreType.hosted, named ) )
        
        g
    }

    // FIXME: Need to reference product space via remote for tracking things in this build.
//    RemoteRepository createRemoteRepository( String named )
//        throws AutoProxRuleException, MalformedURLException
//    {
//        def match = named.split('\\+')
//
//        new RemoteRepository( named, "http://localhost/api/group/product+${match[1]}+${match[2]}" );
//    }
}

