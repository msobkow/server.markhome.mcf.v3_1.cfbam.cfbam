// Description: Java 25 base object instance implementation for ClearTopDep

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamClearTopDepObj
	extends CFBamClearDepObj
	implements ICFBamClearTopDepObj
{
	protected ICFBamTableObj requiredContainerTable;
	protected List<ICFBamClearSubDep1Obj> optionalComponentsClearDep;
	protected ICFBamClearTopDepObj optionalLookupPrev;
	protected ICFBamClearTopDepObj optionalLookupNext;

	public CFBamClearTopDepObj() {
		super();
		requiredContainerTable = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	public CFBamClearTopDepObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerTable = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getClearTopDepTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ClearTopDep" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTable();
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
	public ICFBamScopeObj realise() {
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().realiseClearTopDep(
			(ICFBamClearTopDepObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().reallyDeepDisposeClearTopDep( (ICFBamClearTopDepObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamClearTopDepObj moveUp() {
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().moveUpClearTopDep( this );
		return( (ICFBamClearTopDepObj)retobj );
	}

	@Override
	public ICFBamClearTopDepObj moveDown() {
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().moveDownClearTopDep( this );
		return( (ICFBamClearTopDepObj)retobj );
	}

	@Override
	public ICFBamClearTopDepTableObj getClearTopDepTable() {
		return( ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryClearTopDep().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableClearTopDep().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamClearTopDep ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamClearTopDepRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredLookupRelation = null;
		optionalLookupDefSchema = null;
		requiredContainerTable = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFBamClearTopDep getClearTopDepRec() {
		return( (ICFBamClearTopDep)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamClearTopDepObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamClearTopDepObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().lockClearTopDep( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamClearTopDepEditObj getEditAsClearTopDep() {
		return( (ICFBamClearTopDepEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamScope rec = getRec();
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
			ICFBamScope rec = getRec();
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
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( ( requiredContainerTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getClearTopDepRec().getRequiredTableId(), forceRead );
			}
		}
		return( requiredContainerTable );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> getOptionalComponentsClearDep() {
		List<ICFBamClearSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep1TableObj().readClearSubDep1ByClearTopDepIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> getOptionalComponentsClearDep( boolean forceRead ) {
		List<ICFBamClearSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep1TableObj().readClearSubDep1ByClearTopDepIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupPrev( boolean forceRead ) {
		if( ( optionalLookupPrev == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getClearTopDepRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrev = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getClearTopDepRec().getOptionalPrevId(), forceRead );
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupNext( boolean forceRead ) {
		if( ( optionalLookupNext == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getClearTopDepRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNext = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getClearTopDepRec().getOptionalNextId(), forceRead );
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getClearTopDepRec().getRequiredTableId() );
	}

	@Override
	public String getRequiredName() {
		return( getClearTopDepRec().getRequiredName() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getClearTopDepRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getClearTopDepRec().getOptionalNextId() );
	}
}
