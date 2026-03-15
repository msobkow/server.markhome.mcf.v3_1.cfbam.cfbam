// Description: Java 25 edit object instance implementation for CFBam SchemaRef.

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

public class CFBamSchemaRefEditObj
	extends CFBamScopeEditObj

	implements ICFBamSchemaRefEditObj
{
	protected ICFBamSchemaDefObj requiredContainerSchema;
	protected ICFBamSchemaDefObj optionalLookupRefSchema;
	protected ICFBamSchemaRefObj optionalLookupPrev;
	protected ICFBamSchemaRefObj optionalLookupNext;

	public CFBamSchemaRefEditObj( ICFBamSchemaRefObj argOrig ) {
		super( argOrig );
		requiredContainerSchema = null;
		optionalLookupRefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getSchemaRefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SchemaRef" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamSchemaDefObj scope = getRequiredContainerSchema();
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
		// We realise this so that it's record will get copied to orig during realization
		ICFBamSchemaRefObj retobj = getSchema().getSchemaRefTableObj().realiseSchemaRef( (ICFBamSchemaRefObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSchemaRef().forget();
	}

	@Override
	public ICFBamSchemaRefObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamSchemaRefObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getOrigAsSchemaRef().getSchema()).getSchemaRefTableObj().createSchemaRef( getOrigAsSchemaRef() );
		if( retobj == getOrigAsSchemaRef() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getSchemaRefTableObj().updateSchemaRef( (ICFBamSchemaRefObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSchemaRefTableObj().deleteSchemaRef( getOrigAsSchemaRef() );
		return( null );
	}

	@Override
	public ICFBamSchemaRefTableObj getSchemaRefTable() {
		return( orig.getSchema().getSchemaRefTableObj() );
	}

	@Override
	public ICFBamSchemaRefEditObj getEditAsSchemaRef() {
		return( (ICFBamSchemaRefEditObj)this );
	}

	@Override
	public ICFBamSchemaRefObj getOrigAsSchemaRef() {
		return( (ICFBamSchemaRefObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsSchemaRef().getSchema().getCFBamBackingStore().getFactorySchemaRef().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerSchema = null;
			optionalLookupRefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public ICFBamSchemaRef getSchemaRefRec() {
		return( (ICFBamSchemaRef)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaId() {
		return( getSchemaRefRec().getRequiredSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getSchemaRefRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getSchemaRefRec().getRequiredName() != value ) {
			getSchemaRefRec().setRequiredName( value );
		}
	}

	@Override
	public String getRequiredRefModelName() {
		return( getSchemaRefRec().getRequiredRefModelName() );
	}

	@Override
	public void setRequiredRefModelName( String value ) {
		if( getSchemaRefRec().getRequiredRefModelName() != value ) {
			getSchemaRefRec().setRequiredRefModelName( value );
		}
	}

	@Override
	public String getRequiredIncludeRoot() {
		return( getSchemaRefRec().getRequiredIncludeRoot() );
	}

	@Override
	public void setRequiredIncludeRoot( String value ) {
		if( getSchemaRefRec().getRequiredIncludeRoot() != value ) {
			getSchemaRefRec().setRequiredIncludeRoot( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalRefSchemaId() {
		return( getSchemaRefRec().getOptionalRefSchemaId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getSchemaRefRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getSchemaRefRec().getOptionalNextId() );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchema() {
		return( getRequiredContainerSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchema( boolean forceRead ) {
		if( forceRead || ( requiredContainerSchema == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsSchemaRef().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getSchemaRefRec().getRequiredSchemaId() );
				requiredContainerSchema = obj;
				if( obj != null ) {
					requiredContainerSchema = obj;
				}
			}
		}
		return( requiredContainerSchema );
	}

	@Override
	public void setRequiredContainerSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getSchemaRefRec();
		}
		if( value != null ) {
			requiredContainerSchema = value;
			getSchemaRefRec().setRequiredContainerSchema(value.getSchemaDefRec());
		}
		requiredContainerSchema = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupRefSchema() {
		return( getOptionalLookupRefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupRefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupRefSchema == null ) ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalRefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsSchemaRef().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getSchemaRefRec().getOptionalRefSchemaId() );
				optionalLookupRefSchema = obj;
			}
		}
		return( optionalLookupRefSchema );
	}

	@Override
	public void setOptionalLookupRefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getSchemaRefRec();
		}
		if( value != null ) {
			optionalLookupRefSchema = value;
			getSchemaRefRec().setOptionalLookupRefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupRefSchema = null;
			getSchemaRefRec().setOptionalLookupRefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupRefSchema = value;
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaRefObj obj = ((ICFBamSchemaObj)getOrigAsSchemaRef().getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getSchemaRefRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamSchemaRefObj value ) {
		if( rec == null ) {
			getSchemaRefRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getSchemaRefRec().setOptionalLookupPrev(value.getSchemaRefRec());
		}
		else {
			optionalLookupPrev = null;
			getSchemaRefRec().setOptionalLookupPrev((ICFBamSchemaRef)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaRefObj obj = ((ICFBamSchemaObj)getOrigAsSchemaRef().getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getSchemaRefRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamSchemaRefObj value ) {
		if( rec == null ) {
			getSchemaRefRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getSchemaRefRec().setOptionalLookupNext(value.getSchemaRefRec());
		}
		else {
			optionalLookupNext = null;
			getSchemaRefRec().setOptionalLookupNext((ICFBamSchemaRef)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public void copyRecToOrig() {
		ICFBamSchemaRef origRec = getOrigAsSchemaRef().getSchemaRefRec();
		ICFBamSchemaRef myRec = getSchemaRefRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamSchemaRef origRec = getOrigAsSchemaRef().getSchemaRefRec();
		ICFBamSchemaRef myRec = getSchemaRefRec();
		myRec.set( origRec );
	}
}
