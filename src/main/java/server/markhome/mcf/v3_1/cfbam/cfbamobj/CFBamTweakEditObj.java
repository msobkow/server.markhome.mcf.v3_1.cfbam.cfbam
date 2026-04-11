// Description: Java 25 edit object instance implementation for CFBam Tweak.

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

public class CFBamTweakEditObj
	implements ICFBamTweakEditObj
{
	protected ICFBamTweakObj orig;
	protected ICFBamTweak rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamScopeObj requiredContainerScopeDef;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;

	public CFBamTweakEditObj( ICFBamTweakObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamTweak origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerScopeDef = null;
		optionalLookupDefSchema = null;
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
	public void setCreatedBy( ICFSecSecUserObj value ) {
		createdBy = value;
		if( value != null ) {
			getRec().setCreatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		getRec().setCreatedAt( value );
	}

	@Override
	public void setUpdatedBy( ICFSecSecUserObj value ) {
		updatedBy = value;
		if( value != null ) {
			getRec().setUpdatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		getRec().setUpdatedAt( value );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getTweakTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Tweak" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamScopeObj scope = getRequiredContainerScopeDef();
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
	public ICFLibAnyObj getNamedObject( Class qualifyingClass, String objName ) {
		ICFLibAnyObj topContainer = getObjQualifier( qualifyingClass );
		if( topContainer == null ) {
			return( null );
		}
		ICFLibAnyObj namedObject = topContainer.getNamedObject( objName );
		return( namedObject );
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
	public String getObjFullName() {
		String fullName = getObjName();
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
				fullName = containerName + "." + fullName;
				container = container.getObjScope();
			}
		}
		return( fullName );
	}

	@Override
	public ICFBamTweakObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamTweakObj retobj = getSchema().getTweakTableObj().realiseTweak( (ICFBamTweakObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsTweak().forget();
	}

	@Override
	public ICFBamTweakObj read() {
		ICFBamTweakObj retval = getOrigAsTweak().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamTweakObj read( boolean forceRead ) {
		ICFBamTweakObj retval = getOrigAsTweak().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamTweakObj create() {
		copyRecToOrig();
		ICFBamTweakObj retobj = ((ICFBamSchemaObj)getOrigAsTweak().getSchema()).getTweakTableObj().createTweak( getOrigAsTweak() );
		if( retobj == getOrigAsTweak() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamTweakEditObj update() {
		getSchema().getTweakTableObj().updateTweak( (ICFBamTweakObj)this );
		return( null );
	}

	@Override
	public CFBamTweakEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getTweakTableObj().deleteTweak( getOrigAsTweak() );
		return( null );
	}

	@Override
	public ICFBamTweakTableObj getTweakTable() {
		return( orig.getSchema().getTweakTableObj() );
	}

	@Override
	public ICFBamTweakEditObj getEdit() {
		return( (ICFBamTweakEditObj)this );
	}

	@Override
	public ICFBamTweakEditObj getEditAsTweak() {
		return( (ICFBamTweakEditObj)this );
	}

	@Override
	public ICFBamTweakEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamTweakObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamTweakObj getOrigAsTweak() {
		return( (ICFBamTweakObj)orig );
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFBamTweak getRec() {
		if( rec == null ) {
			rec = getOrigAsTweak().getSchema().getCFBamBackingStore().getFactoryTweak().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamTweak value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerScopeDef = null;
			optionalLookupDefSchema = null;
		}
	}

	@Override
	public ICFBamTweak getTweakRec() {
		return( (ICFBamTweak)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( CFLibDbKeyHash256 value ) {
		orig.setPKey( value );
		copyPKeyToRec();
	}

	@Override
	public boolean getIsNew() {
		return( orig.getIsNew() );
	}

	@Override
	public void setIsNew( boolean value ) {
		orig.setIsNew( value );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTenantId() {
		return( getTweakRec().getRequiredTenantId() );
	}

	@Override
	public void setRequiredTenantId( CFLibDbKeyHash256 value ) {
		if( getTweakRec().getRequiredTenantId() != value ) {
			getTweakRec().setRequiredTenantId( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredScopeId() {
		return( getTweakRec().getRequiredScopeId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaTenantId() {
		return( getTweakRec().getOptionalDefSchemaTenantId() );
	}

	@Override
	public void setOptionalDefSchemaTenantId( CFLibDbKeyHash256 value ) {
		if( getTweakRec().getOptionalDefSchemaTenantId() != value ) {
			getTweakRec().setOptionalDefSchemaTenantId( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getTweakRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getTweakRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getTweakRec().getRequiredName() != value ) {
			getTweakRec().setRequiredName( value );
		}
	}

	@Override
	public boolean getRequiredReplacesInherited() {
		return( getTweakRec().getRequiredReplacesInherited() );
	}

	@Override
	public void setRequiredReplacesInherited( boolean value ) {
		if( getTweakRec().getRequiredReplacesInherited() != value ) {
			getTweakRec().setRequiredReplacesInherited( value );
		}
	}

	@Override
	public String getRequiredTweakGelText() {
		return( getTweakRec().getRequiredTweakGelText() );
	}

	@Override
	public void setRequiredTweakGelText( String value ) {
		if( getTweakRec().getRequiredTweakGelText() != value ) {
			getTweakRec().setRequiredTweakGelText( value );
		}
	}

	@Override
	public ICFBamScopeObj getRequiredContainerScopeDef() {
		return( getRequiredContainerScopeDef( false ) );
	}

	@Override
	public ICFBamScopeObj getRequiredContainerScopeDef( boolean forceRead ) {
		if( forceRead || ( requiredContainerScopeDef == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamScopeObj obj = ((ICFBamSchemaObj)getOrigAsTweak().getSchema()).getScopeTableObj().readScopeByIdIdx( getTweakRec().getRequiredScopeId() );
				requiredContainerScopeDef = obj;
				if( obj != null ) {
					requiredContainerScopeDef = obj;
				}
			}
		}
		return( requiredContainerScopeDef );
	}

	@Override
	public void setRequiredContainerScopeDef( ICFBamScopeObj value ) {
		if( rec == null ) {
			getTweakRec();
		}
		if( value != null ) {
			requiredContainerScopeDef = value;
			getTweakRec().setRequiredContainerScopeDef(value.getScopeRec());
		}
		requiredContainerScopeDef = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getTweakRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsTweak().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getTweakRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getTweakRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getTweakRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getTweakRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				setPKey(rec.getPKey());
			}
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamTweak origRec = getOrigAsTweak().getTweakRec();
		ICFBamTweak myRec = getTweakRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamTweak origRec = getOrigAsTweak().getTweakRec();
		ICFBamTweak myRec = getTweakRec();
		myRec.set( origRec );
	}
}
