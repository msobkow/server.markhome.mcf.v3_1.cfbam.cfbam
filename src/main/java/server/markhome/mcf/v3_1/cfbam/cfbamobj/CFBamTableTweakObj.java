// Description: Java 25 base object instance implementation for TableTweak

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

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamTableTweakObj
	extends CFBamTweakObj
	implements ICFBamTableTweakObj
{
	protected ICFBamTableObj requiredContainerTableDef;

	public CFBamTableTweakObj() {
		super();
		requiredContainerTableDef = null;
	}

	public CFBamTableTweakObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerTableDef = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getTableTweakTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "TableTweak" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTableDef();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else if( container instanceof ICFBamSchemaDefObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public ICFBamTweakObj realise() {
		ICFBamTableTweakObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().realiseTableTweak(
			(ICFBamTableTweakObj)this );
		return( (ICFBamTweakObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().reallyDeepDisposeTableTweak( (ICFBamTableTweakObj)this );
	}

	@Override
	public ICFBamTweakObj read() {
		ICFBamTableTweakObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().readTableTweakByIdIdx( getPKey(), false );
		return( (ICFBamTweakObj)retobj );
	}

	@Override
	public ICFBamTweakObj read( boolean forceRead ) {
		ICFBamTableTweakObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().readTableTweakByIdIdx( getPKey(), forceRead );
		return( (ICFBamTweakObj)retobj );
	}

	@Override
	public ICFBamTableTweakTableObj getTableTweakTable() {
		return( ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj() );
	}

	@Override
	public ICFBamTweak getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryTableTweak().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableTableTweak().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamTweak value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamTableTweak ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamTableTweakRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerScopeDef = null;
		optionalLookupDefSchema = null;
		requiredContainerTableDef = null;
	}

	@Override
	public ICFBamTableTweak getTableTweakRec() {
		return( (ICFBamTableTweak)getRec() );
	}

	@Override
	public ICFBamTweakEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamTableTweakObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamTableTweakObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().lockTableTweak( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().newEditInstance( lockobj );
		return( (ICFBamTweakEditObj)edit );
	}

	@Override
	public ICFBamTableTweakEditObj getEditAsTableTweak() {
		return( (ICFBamTableTweakEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamTweak rec = getRec();
			createdBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getCreatedByUserId() );
		}
		return( createdBy );
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( getRec().getCreatedAt() );
	}

	@Override
	public ICFSecSecUserObj getUpdatedBy() {
		if( updatedBy == null ) {
			ICFBamTweak rec = getRec();
			updatedBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTableDef() {
		return( getRequiredContainerTableDef( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTableDef( boolean forceRead ) {
		if( ( requiredContainerTableDef == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerTableDef = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getTableTweakRec().getRequiredTableId(), forceRead );
			}
		}
		return( requiredContainerTableDef );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getTableTweakRec().getRequiredTableId() );
	}
}
