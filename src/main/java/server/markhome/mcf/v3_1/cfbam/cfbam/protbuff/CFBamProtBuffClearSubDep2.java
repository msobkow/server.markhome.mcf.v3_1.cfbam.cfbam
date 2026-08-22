// Description: Java 25 implJustProtementation of a ClearSubDep2 buffer

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbam.buff;

import java.lang.reflect.*;
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public class CFBamProtBuffClearSubDep2
	extends CFBamProtBuffClearDep
	implements ICFBamClearSubDep2
{
	protected ICFLibKeyHash256 requiredClearSubDep1Id;
	protected String requiredName;

	public CFBamProtBuffClearSubDep2() {
		super();
		requiredClearSubDep1Id = CFLibDbKeyHash256.fromHex( ICFBamPubClearSubDep2.CLEARSUBDEP1ID_INIT_VALUE.toString() );
		requiredName = ICFBamPubClearSubDep2.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamClearSubDep2.CLASS_CODE );
	}

	@Override
	public ICFBamClearSubDep1 getRequiredContainerClearSubDep1() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep1Table targetTable = targetBackingCFBam.getTableClearSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep1()");
		}
		ICFBamClearSubDep1 targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredClearSubDep1Id());
		return(targetRec);
	}

	@Override
	public ICFBamClearSubDep1 getRequiredContainerClearSubDep1() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep1Table targetTable = targetBackingCFBam.getTableClearSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep1()");
		}
		ICFBamClearSubDep1 targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredClearSubDep1Id());
		return(targetRec);
	}

	@Override
	public ICFBamClearSubDep1 getRequiredContainerClearSubDep1() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep1Table targetTable = targetBackingCFBam.getTableClearSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep1()");
		}
		ICFBamPubClearSubDep1 targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredClearSubDep1Id());
		return(targetRec);
	}

	@Override
	public void setJustProtRequiredContainerClearSubDep1(ICFLibKeyHash256 argClearSubDep1Id) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerClearSubDep1-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamProtClearSubDep1Table targetTable = targetBackingCFBam.getTableClearSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamProtClearSubDep1 found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argClearSubDep1Id);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerClearSubDep1-args", 0, "found");
		}
		else if ((found instanceof ICFBamProtClearSubDep1) || (found instanceof ICFBamPubClearSubDep1)) {
		requiredClearSubDep1Id = argClearSubDep1Id;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerClearSubDep1-args", "found", found, "ICFBamProtClearSubDep1ICFBamPubClearSubDep1");
		}
	}

	@Override
	public void setJustProtRequiredContainerClearSubDep1(ICFBamClearSubDep1 argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerClearSubDep1", 1, "argObj");
		}
		else {
			setJustProtRequiredClearSubDep1Id(argObj.getRequiredId());
		}
	}

	@Override
	public List<ICFBamClearSubDep3> getOptionalComponentsClearDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep3Table targetTable = targetBackingCFBam.getTableClearSubDep3();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep3()");
		}
		ICFBamClearSubDep3[] targetArr = targetTable.readDerivedByClearSubDep2Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamClearSubDep3> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamClearSubDep3> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFBamClearSubDep3> getOptionalComponentsClearDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep3Table targetTable = targetBackingCFBam.getTableClearSubDep3();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep3()");
		}
		ICFBamClearSubDep3[] targetArr = targetTable.readDerivedByClearSubDep2Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamClearSubDep3> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamClearSubDep3> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFBamClearSubDep3> getOptionalComponentsClearDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep3Table targetTable = targetBackingCFBam.getTableClearSubDep3();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsClearDep", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep3()");
		}
		ICFBamPubClearSubDep3[] targetArr = targetTable.readDerivedByClearSubDep2Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamPubClearSubDep3> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamPubClearSubDep3> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredClearSubDep1Id() {
		return(requiredClearSubDep1Id);
	}

	public void setRequiredClearSubDep1Id( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredClearSubDep1Id",
				1,
				"value" );
		}
		requiredClearSubDep1Id = value;
	}

	@Override
	public String getRequiredName() {
		return(requiredName);
	}

	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 192 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				192 );
		}
		requiredName = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamProtClearSubDep2 rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2H rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamScopeHPKey ) {
			ICFBamProtScopeHPKey rhs = (ICFBamScopeHPKey)obj;
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByClearSubDep1IdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByUNameIdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2 rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2H rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtScopeHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByClearSubDep1IdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByUNameIdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubClearSubDep2 rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubClearSubDep2H rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubScopeHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByClearSubDep1IdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtClearSubDep2ByUNameIdxKey rhs ) {
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredClearSubDep1Id().hashCode();
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamProtClearSubDep2 rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFBamProtScopeHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtClearSubDep2H rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamClearSubDep2ByClearSubDep1IdxKey rhs ) {
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamClearSubDep2ByUNameIdxKey rhs ) {
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubClearSubDep2 rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubScopeHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubClearSubDep2H rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubClearSubDep2ByClearSubDep1IdxKey rhs ) {
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubClearSubDep2ByUNameIdxKey rhs ) {
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			cmp = super.compareTo( obj );
			return( cmp );
		}
	}

	@Override
	public void setJustProt( ICFBamScope src ) {
		if( src instanceof CFBamProtBuffClearSubDep2 ) {
			setJustProtClearSubDep2( (CFBamProtBuffClearSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffClearSubDep2" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamClearSubDep2 src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamScopeH src ) {
		if( src instanceof ICFBamProtClearSubDep2H ) {
			setClearSubDep2( (ICFBamProtClearSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearSubDep2H" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamClearSubDep2H src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamProtScope src ) {
		if( src instanceof CFBamProtBuffClearSubDep2 ) {
			setJustProtClearSubDep2( (CFBamProtBuffClearSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffClearSubDep2" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamProtClearSubDep2 src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamProtScopeH src ) {
		if( src instanceof ICFBamProtClearSubDep2H ) {
			setClearSubDep2( (ICFBamProtClearSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearSubDep2H" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamProtClearSubDep2H src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamPubScope src ) {
		if( src instanceof CFBamPubProtBuffClearSubDep2 ) {
			setJustProtClearSubDep2( (CFBamPubProtBuffClearSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubProtBuffClearSubDep2" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamPubClearSubDep2 src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamPubScopeH src ) {
		if( src instanceof ICFBamPubClearSubDep2H ) {
			setClearSubDep2( (ICFBamPubClearSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearSubDep2H" );
		}
	}

	@Override
	public void setJustProtClearSubDep2( ICFBamPubClearSubDep2H src ) {
		super.setJustProtClearDep( src );
		setJustProtRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setJustProtRequiredClearSubDep1Id(src.getRequiredClearSubDep1Id());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredClearSubDep1Id=" + "\"" + getRequiredClearSubDep1Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamProtBuffClearSubDep2" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
