/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.util.resources.provider;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.resources.LocaleData;

/**
 * {@code LocaleDataProvider} in module jdk.localedata implements
 * {@code LocaleDataBundleProvider} in module java.base. This class works as a
 * service agent between {@code ResourceBundle.getBundle} callers in java.base
 * and resource bundles in jdk.localedata.
 */
public class LocaleDataProvider implements sun.text.resources.BreakIteratorInfoProvider,
                                           sun.text.resources.BreakIteratorRulesProvider,
                                           sun.text.resources.FormatDataProvider,
                                           sun.text.resources.CollationDataProvider,
                                           sun.util.resources.LocaleNamesProvider,
                                           sun.util.resources.TimeZoneNamesProvider,
                                           sun.util.resources.CalendarDataProvider,
                                           sun.util.resources.CurrencyNamesProvider {
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale) {
        ResourceBundle.Control control = LocaleData.getLocaleDataResourceBundleControl();
        try {
            ClassLoader loader = LocaleDataProvider.class.getClassLoader();
            return control.newBundle(baseName, locale, "java.class", loader, false);
        } catch (IllegalAccessException | InstantiationException | IOException e) {
        }
        return null;
    }
}
