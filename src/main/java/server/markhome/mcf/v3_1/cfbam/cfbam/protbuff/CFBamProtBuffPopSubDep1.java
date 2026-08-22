// Description: Java 25 implJustProtementation of a PopSubDep1 buffer

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

public class CFBamProtBuffPopSubDep1
	extends CFBamProtBuffPopDep
	implements ICFBamPopSubDep1
{
	protected ICFLibKeyHash256 requiredPopTopDepId;
	protected String requiredName;

	public CFBamProtBuffPopSubDep1() {
		super();
		requiredPopTopDepId = CFLibDbKeyHash256.fromHex( ICFBamPubPopSubDep1.POPTOPDEPID_INIT_VALUE.toString() );
		requiredName = ICFBamPubPopSubDep1.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamPopSubDep1.CLASS_CODE );
	}

	@Override
	public ICFBamPopTopDep getRequiredContainerContPopTopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopTopDepTable targetTable = targetBackingCFBam.getTablePopTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopTopDep()");
		}
		ICFBamPopTopDep targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredPopTopDepId());
		return(targetRec);
	}

	@Override
	public ICFBamPopTopDep getRequiredContainerContPopTopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopTopDepTable targetTable = targetBackingCFBam.getTablePopTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopTopDep()");
		}
		ICFBamPopTopDep targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredPopTopDepId());
		return(targetRec);
	}

	@Override
	public ICFBamPopTopDep getRequiredContainerContPopTopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopTopDepTable targetTable = targetBackingCFBam.getTablePopTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopTopDep()");
		}
		ICFBamPubPopTopDep targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredPopTopDepId());
		return(targetRec);
	}

	@Override
	public void setJustProtRequiredContainerContPopTopDep(ICFLibKeyHash256 argPopTopDepId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerContPopTopDep-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamProtPopTopDepTable targetTable = targetBackingCFBam.getTablePopTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerContPopTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamProtPopTopDep found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argPopTopDepId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerContPopTopDep-args", 0, "found");
		}
		else if ((found instanceof ICFBamProtPopTopDep) || (found instanceof ICFBamPubPopTopDep)) {
		requiredPopTopDepId = argPopTopDepId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerContPopTopDep-args", "found", found, "ICFBamProtPopTopDepICFBamPubPopTopDep");
		}
	}

	@Override
	public void setJustProtRequiredContainerContPopTopDep(ICFBamPopTopDep argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerContPopTopDep", 1, "argObj");
		}
		else {
			setJustProtRequiredPopTopDepId(argObj.getRequiredId());
		}
	}

	@Override
	public List<ICFBamPopSubDep2> getOptionalComponentsPopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopSubDep2Table targetTable = targetBackingCFBam.getTablePopSubDep2();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopSubDep2()");
		}
		ICFBamPopSubDep2[] targetArr = targetTable.readDerivedByPopSubDep1Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamPopSubDep2> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamPopSubDep2> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFBamPopSubDep2> getOptionalComponentsPopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopSubDep2Table targetTable = targetBackingCFBam.getTablePopSubDep2();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopSubDep2()");
		}
		ICFBamPopSubDep2[] targetArr = targetTable.readDerivedByPopSubDep1Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamPopSubDep2> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamPopSubDep2> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFBamPopSubDep2> getOptionalComponentsPopDep() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopSubDep2Table targetTable = targetBackingCFBam.getTablePopSubDep2();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPopDep", 0, "ICFBamSchema.getBackingCFBam().getTablePopSubDep2()");
		}
		ICFBamPubPopSubDep2[] targetArr = targetTable.readDerivedByPopSubDep1Idx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamPubPopSubDep2> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamPubPopSubDep2> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredPopTopDepId() {
		return(requiredPopTopDepId);
	}

	public void setRequiredPopTopDepId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPopTopDepId",
				1,
				"value" );
		}
		requiredPopTopDepId = value;
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
		else if( obj instanceof ICFBamProtPopSubDep1 rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1H rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1ByPopTopDepIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtPopSubDep1ByUNameIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1 rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1H rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1ByPopTopDepIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtPopSubDep1ByUNameIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamPubPopSubDep1 rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamPubPopSubDep1H rhs ) {
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
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		else if( obj instanceof ICFBamProtPopSubDep1ByPopTopDepIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtPopSubDep1ByUNameIdxKey rhs ) {
			if( getRequiredPopTopDepId() != null ) {
				if( rhs.getRequiredPopTopDepId() != null ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null ) {
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
		hashCode = hashCode + getRequiredPopTopDepId().hashCode();
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
		else if( obj instanceof ICFBamProtPopSubDep1 rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		else if( obj instanceof ICFBamProtPopSubDep1H rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		else if( obj instanceof ICFBamPopSubDep1ByPopTopDepIdxKey rhs ) {
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPopSubDep1ByUNameIdxKey rhs ) {
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		else if( obj instanceof ICFBamPubPopSubDep1 rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		else if( obj instanceof ICFBamPubPopSubDep1H rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		else if( obj instanceof ICFBamPubPopSubDep1ByPopTopDepIdxKey rhs ) {
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubPopSubDep1ByUNameIdxKey rhs ) {
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
		if( src instanceof CFBamProtBuffPopSubDep1 ) {
			setJustProtPopSubDep1( (CFBamProtBuffPopSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffPopSubDep1" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamPopSubDep1 src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamScopeH src ) {
		if( src instanceof ICFBamProtPopSubDep1H ) {
			setPopSubDep1( (ICFBamProtPopSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep1H" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamPopSubDep1H src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamProtScope src ) {
		if( src instanceof CFBamProtBuffPopSubDep1 ) {
			setJustProtPopSubDep1( (CFBamProtBuffPopSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffPopSubDep1" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamProtPopSubDep1 src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamProtScopeH src ) {
		if( src instanceof ICFBamProtPopSubDep1H ) {
			setPopSubDep1( (ICFBamProtPopSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep1H" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamProtPopSubDep1H src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamPubScope src ) {
		if( src instanceof CFBamPubProtBuffPopSubDep1 ) {
			setJustProtPopSubDep1( (CFBamPubProtBuffPopSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubProtBuffPopSubDep1" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamPubPopSubDep1 src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void setJustProt( ICFBamPubScopeH src ) {
		if( src instanceof ICFBamPubPopSubDep1H ) {
			setPopSubDep1( (ICFBamPubPopSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep1H" );
		}
	}

	@Override
	public void setJustProtPopSubDep1( ICFBamPubPopSubDep1H src ) {
		super.setJustProtPopDep( src );
		setJustProtRequiredContainerContPopTopDep(src.getRequiredContainerContPopTopDep());
		setJustProtRequiredPopTopDepId(src.getRequiredPopTopDepId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredPopTopDepId=" + "\"" + getRequiredPopTopDepId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamProtBuffPopSubDep1" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
