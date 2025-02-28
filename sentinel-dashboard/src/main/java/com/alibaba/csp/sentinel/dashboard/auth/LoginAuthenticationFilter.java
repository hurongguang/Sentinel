/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.csp.sentinel.dashboard.auth;

import javax.servlet.Filter;

/**
 * <p>The Servlet filter for authentication.</p>
 *
 * <p>Note: some urls are excluded as they needn't auth, such as:</p>
 * <ul>
 * <li>index url: {@code /}</li>
 * <li>authentication request url: {@code /login}, {@code /logout}</li>
 * <li>machine registry: {@code /registry/machine}</li>
 * <li>static resources</li>
 * </ul>
 * <p>
 * The excluded urls and urlSuffixes could be configured in {@code application.back} file.
 *
 * @author cdfive
 * @author wxq
 * @since 1.6.0
 */
public interface LoginAuthenticationFilter extends Filter {

}
