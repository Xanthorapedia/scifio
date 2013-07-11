/*
 * #%L
 * SCIFIO library for reading and converting scientific file formats.
 * %%
 * Copyright (C) 2011 - 2013 Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */
package io.scif.filters;

import io.scif.Metadata;
import io.scif.Reader;

import java.util.Set;

import org.scijava.InstantiableException;


/**
 * {@link io.scif.filters.MasterFilter} for wrapping {@link io.scif.Reader} components.
 * 
 * @author Mark Hiner
 *
 * @see io.scif.Reader
 */
public class ReaderFilter extends AbstractReaderFilter implements MasterFilter<Reader> {
  
  // -- Fields --
  
  private MasterFilterHelper<Reader> fHelper;
  
  // -- Constructor --
  
  /**
   * @param r - Reader to be wrapped
   */
  public ReaderFilter(Reader r) {
    fHelper = new MasterFilterHelper<Reader>(r, Reader.class);
  }
  
  // -- MasterFilter API Methods --

  /*
   * @see io.scif.filters.MasterFilter#enable(java.lang.Class)
   */
  public <F extends Filter> F enable(Class<F> filterClass) throws InstantiableException {
    return fHelper.enable(filterClass);
  }

  /*
   * @see io.scif.filters.MasterFilter#disable(java.lang.Class)
   */
  public boolean disable(Class<? extends Filter> filterClass) throws InstantiableException {
    return fHelper.disable(filterClass);
  }

  /*
   * @see io.scif.filters.MasterFilter#getFilterClasses()
   */
  public Set<Class<? extends Filter>> getFilterClasses() {
    return fHelper.getFilterClasses();
  }

  // -- Filter API Methods --
  
  /*
   * @see io.scif.filters.MasterFilter#setParent(java.lang.Object)
   */
  @Override
  public void setParent(Object parent) {
    fHelper.setParent(parent);
  }
  
  /*
   * @see io.scif.filters.MasterFilter#getParent()
   */
  @Override
  public Reader getParent() {
    return fHelper.getParent();
  }
  
  
  public Reader getTail() {
    return fHelper.getTail();
  }
  
  /*
   * @see io.scif.filters.MasterFilter#reset()
   */
  @Override
  public void reset() {
    fHelper.reset();
  }
  
  // -- Reader API Methods --
  
  /*
   * @see io.scif.filters.AbstractReaderFilter#getMetadata()
   */
  public Metadata getMetadata() {
    return fHelper.getParent().getMetadata();
  }
}