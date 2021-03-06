/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package security;

import be.objectify.deadbolt.AbstractDeadboltHandler;
import be.objectify.deadbolt.DynamicResourceHandler;
import be.objectify.deadbolt.models.RoleHolder;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import views.html.accessFailed;

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
public class BuggyDeadboltHandler extends AbstractDeadboltHandler
{
    public Result beforeRoleCheck(Http.Context context)
    {
        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return null;
    }

    public RoleHolder getRoleHolder(Http.Context context)
    {
        // in a real application, the user name would probably be in the session following a login process
        return User.findByUserName("steve");
    }

    public DynamicResourceHandler getDynamicResourceHandler(Http.Context context)
    {
        return new MyDynamicResourceHandler();
    }

    @Override
    public Result onAccessFailure(Http.Context context,
                                  String content)
    {
        throw new RuntimeException("An exception occurred in onAccessFailure");
    }
}
