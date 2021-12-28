/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.vfs2.provider.sftp;

import java.io.OutputStream;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VfsSftpTest {
    private static final Logger log = LoggerFactory.getLogger(VfsSftpTest.class);


    @Test
    public void testCopyFrom() throws Exception {
        final FileSystemManager manager = VFS.getManager();


        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{

                        final FileObject from = manager.resolveFile("file:///d:/tmp/j.json");
                        FileObject toFileObject = manager
                            .resolveFile("sftp://build:build@10.10.3.106/testRoot/123083123test.txt");
                        toFileObject.copyFrom(from, Selectors.SELECT_ALL);
//                        try(OutputStream outputStream = toFileObject.getContent().getOutputStream()){
//                            // do nothing
//                        }
                        System.out.println("ok");
                    }catch (Exception ex) {
                        log.error("transfer ex:", ex);
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
            t.join();


        }

    }
}
