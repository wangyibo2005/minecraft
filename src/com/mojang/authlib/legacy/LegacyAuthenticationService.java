// 
// Decompiled by Procyon v0.6-prerelease
// 

package com.mojang.authlib.legacy;

import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.GameProfileRepository;
import org.apache.commons.lang3.Validate;
import com.mojang.authlib.Agent;
import java.net.Proxy;
import com.mojang.authlib.HttpAuthenticationService;

@Deprecated
public class LegacyAuthenticationService extends HttpAuthenticationService
{
    protected LegacyAuthenticationService(final Proxy proxy) {
        super(proxy);
    }
    
    @Override
    public LegacyUserAuthentication createUserAuthentication(final Agent agent) {
        Validate.notNull((Object)agent);
        if (agent != Agent.MINECRAFT) {
            throw new IllegalArgumentException("Legacy authentication cannot handle anything but Minecraft");
        }
        return new LegacyUserAuthentication(this);
    }
    
    @Override
    public LegacyMinecraftSessionService createMinecraftSessionService() {
        return new LegacyMinecraftSessionService(this);
    }
    
    @Override
    public GameProfileRepository createProfileRepository() {
        throw new UnsupportedOperationException("Legacy authentication service has no profile repository");
    }
}
