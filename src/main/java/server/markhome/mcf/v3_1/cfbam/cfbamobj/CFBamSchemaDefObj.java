// Description: Java 25 base object instance implementation for SchemaDef

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

public class CFBamSchemaDefObj
	extends CFBamScopeObj
	implements ICFBamSchemaDefObj
{
	protected ICFIntMinorVersionObj requiredContainerMinorVersion;
	protected List<ICFBamTableObj> optionalComponentsTables;
	protected List<ICFBamValueObj> optionalComponentsTypes;
	protected List<ICFBamSchemaRefObj> optionalComponentsSchemaRefs;
	protected List<ICFBamTweakObj> optionalComponentsTweaks;
	protected List<ICFBamSchemaRoleObj> optionalComponentsRoles;
	protected ICFSecTenantObj requiredOwnerCTenant;

	public CFBamSchemaDefObj() {
		super();
		requiredContainerMinorVersion = null;
		requiredOwnerCTenant = null;
	}

	public CFBamSchemaDefObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerMinorVersion = null;
		requiredOwnerCTenant = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getSchemaDefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SchemaDef" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFIntMinorVersionObj scope = getRequiredContainerMinorVersion();
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
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
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
			else if( container instanceof ICFBamSubProjectObj ) {
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
		ICFBamSchemaDefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().realiseSchemaDef(
			(ICFBamSchemaDefObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().reallyDeepDisposeSchemaDef( (ICFBamSchemaDefObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamSchemaDefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamSchemaDefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamSchemaDefTableObj getSchemaDefTable() {
		return( ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactorySchemaDef().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableSchemaDef().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamSchemaDef ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamSchemaDefRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerMinorVersion = null;
		requiredOwnerCTenant = null;
	}

	@Override
	public ICFBamSchemaDef getSchemaDefRec() {
		return( (ICFBamSchemaDef)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamSchemaDefObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamSchemaDefObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().lockSchemaDef( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamSchemaDefEditObj getEditAsSchemaDef() {
		return( (ICFBamSchemaDefEditObj)edit );
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
	public ICFIntMinorVersionObj getRequiredContainerMinorVersion() {
		return( getRequiredContainerMinorVersion( false ) );
	}

	@Override
	public ICFIntMinorVersionObj getRequiredContainerMinorVersion( boolean forceRead ) {
		if( ( requiredContainerMinorVersion == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerMinorVersion = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByIdIdx( getSchemaDefRec().getRequiredMinorVersionId(), forceRead );
			}
		}
		return( requiredContainerMinorVersion );
	}

	@Override
	public List<ICFBamTableObj> getOptionalComponentsTables() {
		List<ICFBamTableObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableBySchemaDefIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTableObj> getOptionalComponentsTables( boolean forceRead ) {
		List<ICFBamTableObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableBySchemaDefIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsTypes() {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsTypes( boolean forceRead ) {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRefObj> getOptionalComponentsSchemaRefs() {
		List<ICFBamSchemaRefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefBySchemaIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRefObj> getOptionalComponentsSchemaRefs( boolean forceRead ) {
		List<ICFBamSchemaRefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefBySchemaIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks() {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks( boolean forceRead ) {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRoleObj> getOptionalComponentsRoles() {
		List<ICFBamSchemaRoleObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRoleTableObj().readSchemaRoleBySchemaIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRoleObj> getOptionalComponentsRoles( boolean forceRead ) {
		List<ICFBamSchemaRoleObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRoleTableObj().readSchemaRoleBySchemaIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerCTenant() {
		return( getRequiredOwnerCTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerCTenant( boolean forceRead ) {
		if( ( requiredOwnerCTenant == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredOwnerCTenant = ((ICFBamSchemaObj)getSchema()).getTenantTableObj().readTenantByIdIdx( getSchemaDefRec().getRequiredCTenantId(), forceRead );
			}
		}
		return( requiredOwnerCTenant );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredCTenantId() {
		return( getSchemaDefRec().getRequiredCTenantId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredMinorVersionId() {
		return( getSchemaDefRec().getRequiredMinorVersionId() );
	}

	@Override
	public String getRequiredName() {
		return( getSchemaDefRec().getRequiredName() );
	}

	@Override
	public String getOptionalDbName() {
		return( getSchemaDefRec().getOptionalDbName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getSchemaDefRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getSchemaDefRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getSchemaDefRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getSchemaDefRec().getOptionalDescription() );
	}

	@Override
	public String getRequiredCopyrightPeriod() {
		return( getSchemaDefRec().getRequiredCopyrightPeriod() );
	}

	@Override
	public String getRequiredCopyrightHolder() {
		return( getSchemaDefRec().getRequiredCopyrightHolder() );
	}

	@Override
	public String getRequiredAuthorEMail() {
		return( getSchemaDefRec().getRequiredAuthorEMail() );
	}

	@Override
	public String getRequiredProjectURL() {
		return( getSchemaDefRec().getRequiredProjectURL() );
	}

	@Override
	public String getRequiredPublishURI() {
		return( getSchemaDefRec().getRequiredPublishURI() );
	}
}
