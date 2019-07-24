/**
 * The MIT License
 * Copyright (c) 2018 Estonian Information System Authority (RIA),
 * Nordic Institute for Interoperability Solutions (NIIS), Population Register Centre (VRK)
 * Copyright (c) 2015-2017 Estonian Information System Authority (RIA), Population Register Centre (VRK)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.niis.xroad.restapi.converter;

import ee.ria.xroad.common.conf.serverconf.model.DescriptionType;

import lombok.Getter;
import org.niis.xroad.restapi.openapi.model.ServiceType;

import java.util.Arrays;
import java.util.Optional;

/**
 * Mapping between ServiceType in api (enum) and model (DescriptionType)
 */
@Getter
public enum ServiceTypeMapping {
    NOSSL(DescriptionType.WSDL, ServiceType.WSDL),
    SSLNOAUTH(DescriptionType.OPENAPI3, ServiceType.REST);

    private final DescriptionType descriptionType;
    private final ServiceType serviceType;

    ServiceTypeMapping(DescriptionType descriptionType, ServiceType serviceType) {
        this.descriptionType = descriptionType;
        this.serviceType = serviceType;
    }

    /**
     * Return matching ServiceType, if any
     * @param descriptionType
     * @return
     */
    public static Optional<ServiceType> map(DescriptionType descriptionType) {
        return getFor(descriptionType).map(ServiceTypeMapping::getServiceType);
    }

    /**
     * Return matching DescriptionType, if any
     * @param serviceType
     * @return
     */
    public static Optional<DescriptionType> map(ServiceType serviceType) {
        return getFor(serviceType).map(ServiceTypeMapping::getDescriptionType);
    }

    /**
     * return ServiceTypeMapping matching the given descriptionType, if any
     * @param descriptionType
     * @return
     */
    public static Optional<ServiceTypeMapping> getFor(DescriptionType descriptionType) {
        return Arrays.stream(values())
                .filter(mapping -> mapping.descriptionType.equals(descriptionType))
                .findFirst();
    }

    /**
     * return ServiceTypeMapping matching the given serviceType, if any
     * @param serviceType
     * @return
     */
    public static Optional<ServiceTypeMapping> getFor(ServiceType serviceType) {
        return Arrays.stream(values())
                .filter(mapping -> mapping.serviceType.equals(serviceType))
                .findFirst();
    }

}