/*
 * Copyright 2013 Basho Technologies Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basho.riak.client.operations;

import com.basho.riak.client.core.RiakCluster;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Collections.unmodifiableList;

public abstract class MultiFetch extends RiakCommand<MultiFetch.Response>
{

    @Override
    abstract Response execute(RiakCluster cluster) throws ExecutionException, InterruptedException;

    public static MultiFetch multiFetch(Key... keys)
    {
        return new KeyMultiFetch(keys);
    }

    public static MultiFetch multiFetch(Iterable<Key> keys)
    {
        return new KeyMultiFetch(keys);
    }

    public static final class Response<T> implements Iterable<FetchValue.Response<T>>
    {

        private final List<FetchValue.Response<T>> responses;

        Response(List<FetchValue.Response<T>> responses)
        {
            this.responses = responses;
        }

        @Override
        public Iterator<FetchValue.Response<T>> iterator()
        {
            return unmodifiableList(responses).iterator();
        }
    }

}
