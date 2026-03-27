// Description: Java 25 Object interface for CFBam Table.

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

public interface ICFBamTableObj
	extends ICFBamScopeObj
{
	/**
	 *	Get the current edition of this Table instance as a ICFBamTableEditObj.
	 *
	 *	@return	The ICFBamTableEditObj edition of this instance.
	 */
	ICFBamTableEditObj getEditAsTable();

	/**
	 *	Get the ICFBamTableTableObj table cache which manages this instance.
	 *
	 *	@return	ICFBamTableTableObj table cache which manages this instance.
	 */
	ICFBamTableTableObj getTableTable();

	/**
	 *	Get the ICFBamTable instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamTable instance which currently backs this object.
	 */
	ICFBamTable getTableRec();

	/**
	 *	Get the required ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 *
	 *	@return	The required ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchemaDef();

	/**
	 *	Get the required ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 *
	 *	@return	The required ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchemaDef( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema();

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamRelationObj array of instances referenced by the Relation key.
	 *
	 *	@return	The optional ICFBamRelationObj[] array of instances referenced by the Relation key.
	 */
	List<ICFBamRelationObj> getOptionalComponentsRelation();

	/**
	 *	Get the array of optional ICFBamRelationObj array of instances referenced by the Relation key.
	 *
	 *	@return	The optional ICFBamRelationObj[] array of instances referenced by the Relation key.
	 */
	List<ICFBamRelationObj> getOptionalComponentsRelation( boolean forceRead );

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the LookupIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the LookupIndex key.
	 */
	ICFBamIndexObj getOptionalLookupLookupIndex();

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the LookupIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the LookupIndex key.
	 */
	ICFBamIndexObj getOptionalLookupLookupIndex( boolean forceRead );

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the AltIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the AltIndex key.
	 */
	ICFBamIndexObj getOptionalLookupAltIndex();

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the AltIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the AltIndex key.
	 */
	ICFBamIndexObj getOptionalLookupAltIndex( boolean forceRead );

	/**
	 *	Get the optional ICFBamTableObj instance referenced by the QualTable key.
	 *
	 *	@return	The optional ICFBamTableObj instance referenced by the QualTable key.
	 */
	ICFBamTableObj getOptionalLookupQualTable();

	/**
	 *	Get the optional ICFBamTableObj instance referenced by the QualTable key.
	 *
	 *	@return	The optional ICFBamTableObj instance referenced by the QualTable key.
	 */
	ICFBamTableObj getOptionalLookupQualTable( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamIndexObj array of instances referenced by the Index key.
	 *
	 *	@return	The optional ICFBamIndexObj[] array of instances referenced by the Index key.
	 */
	List<ICFBamIndexObj> getOptionalComponentsIndex();

	/**
	 *	Get the array of optional ICFBamIndexObj array of instances referenced by the Index key.
	 *
	 *	@return	The optional ICFBamIndexObj[] array of instances referenced by the Index key.
	 */
	List<ICFBamIndexObj> getOptionalComponentsIndex( boolean forceRead );

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 */
	ICFBamIndexObj getOptionalLookupPrimaryIndex();

	/**
	 *	Get the optional ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 *
	 *	@return	The optional ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 */
	ICFBamIndexObj getOptionalLookupPrimaryIndex( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamValueObj array of instances referenced by the Columns key.
	 *
	 *	@return	The optional ICFBamValueObj[] array of instances referenced by the Columns key.
	 */
	List<ICFBamValueObj> getOptionalComponentsColumns();

	/**
	 *	Get the array of optional ICFBamValueObj array of instances referenced by the Columns key.
	 *
	 *	@return	The optional ICFBamValueObj[] array of instances referenced by the Columns key.
	 */
	List<ICFBamValueObj> getOptionalComponentsColumns( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamRelationObj array of instances referenced by the ReverseRelations key.
	 *
	 *	@return	The optional ICFBamRelationObj[] array of instances referenced by the ReverseRelations key.
	 */
	List<ICFBamRelationObj> getOptionalChildrenReverseRelations();

	/**
	 *	Get the array of optional ICFBamRelationObj array of instances referenced by the ReverseRelations key.
	 *
	 *	@return	The optional ICFBamRelationObj[] array of instances referenced by the ReverseRelations key.
	 */
	List<ICFBamRelationObj> getOptionalChildrenReverseRelations( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamChainObj array of instances referenced by the Chains key.
	 *
	 *	@return	The optional ICFBamChainObj[] array of instances referenced by the Chains key.
	 */
	List<ICFBamChainObj> getOptionalComponentsChains();

	/**
	 *	Get the array of optional ICFBamChainObj array of instances referenced by the Chains key.
	 *
	 *	@return	The optional ICFBamChainObj[] array of instances referenced by the Chains key.
	 */
	List<ICFBamChainObj> getOptionalComponentsChains( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamDelTopDepObj array of instances referenced by the DelDep key.
	 *
	 *	@return	The optional ICFBamDelTopDepObj[] array of instances referenced by the DelDep key.
	 */
	List<ICFBamDelTopDepObj> getOptionalComponentsDelDep();

	/**
	 *	Get the array of optional ICFBamDelTopDepObj array of instances referenced by the DelDep key.
	 *
	 *	@return	The optional ICFBamDelTopDepObj[] array of instances referenced by the DelDep key.
	 */
	List<ICFBamDelTopDepObj> getOptionalComponentsDelDep( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamClearTopDepObj array of instances referenced by the ClearDep key.
	 *
	 *	@return	The optional ICFBamClearTopDepObj[] array of instances referenced by the ClearDep key.
	 */
	List<ICFBamClearTopDepObj> getOptionalComponentsClearDep();

	/**
	 *	Get the array of optional ICFBamClearTopDepObj array of instances referenced by the ClearDep key.
	 *
	 *	@return	The optional ICFBamClearTopDepObj[] array of instances referenced by the ClearDep key.
	 */
	List<ICFBamClearTopDepObj> getOptionalComponentsClearDep( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamServerMethodObj array of instances referenced by the ServerMethods key.
	 *
	 *	@return	The optional ICFBamServerMethodObj[] array of instances referenced by the ServerMethods key.
	 */
	List<ICFBamServerMethodObj> getOptionalComponentsServerMethods();

	/**
	 *	Get the array of optional ICFBamServerMethodObj array of instances referenced by the ServerMethods key.
	 *
	 *	@return	The optional ICFBamServerMethodObj[] array of instances referenced by the ServerMethods key.
	 */
	List<ICFBamServerMethodObj> getOptionalComponentsServerMethods( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamTweakObj array of instances referenced by the Tweaks key.
	 *
	 *	@return	The optional ICFBamTweakObj[] array of instances referenced by the Tweaks key.
	 */
	List<ICFBamTweakObj> getOptionalComponentsTweaks();

	/**
	 *	Get the array of optional ICFBamTweakObj array of instances referenced by the Tweaks key.
	 *
	 *	@return	The optional ICFBamTweakObj[] array of instances referenced by the Tweaks key.
	 */
	List<ICFBamTweakObj> getOptionalComponentsTweaks( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SchemaDefId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SchemaDefId.
	 */
	CFLibDbKeyHash256 getRequiredSchemaDefId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute DefSchemaId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute DefSchemaId.
	 */
	CFLibDbKeyHash256 getOptionalDefSchemaId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Get the optional String attribute DbName.
	 *
	 *	@return	The optional String attribute DbName.
	 */
	String getOptionalDbName();

	/**
	 *	Get the optional String attribute ShortName.
	 *
	 *	@return	The optional String attribute ShortName.
	 */
	String getOptionalShortName();

	/**
	 *	Get the optional String attribute Label.
	 *
	 *	@return	The optional String attribute Label.
	 */
	String getOptionalLabel();

	/**
	 *	Get the optional String attribute ShortDescription.
	 *
	 *	@return	The optional String attribute ShortDescription.
	 */
	String getOptionalShortDescription();

	/**
	 *	Get the optional String attribute Description.
	 *
	 *	@return	The optional String attribute Description.
	 */
	String getOptionalDescription();

	/**
	 *	Get the required boolean attribute PageData.
	 *
	 *	@return	The required boolean attribute PageData.
	 */
	boolean getRequiredPageData();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute PrimaryIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute PrimaryIndexId.
	 */
	CFLibDbKeyHash256 getOptionalPrimaryIndexId();

	/**
	 *	Get the required String attribute TableClassCode.
	 *
	 *	@return	The required String attribute TableClassCode.
	 */
	String getRequiredTableClassCode();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute LookupIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute LookupIndexId.
	 */
	CFLibDbKeyHash256 getOptionalLookupIndexId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute AltIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute AltIndexId.
	 */
	CFLibDbKeyHash256 getOptionalAltIndexId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute QualifyingTableId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute QualifyingTableId.
	 */
	CFLibDbKeyHash256 getOptionalQualifyingTableId();

	/**
	 *	Get the required boolean attribute IsInstantiable.
	 *
	 *	@return	The required boolean attribute IsInstantiable.
	 */
	boolean getRequiredIsInstantiable();

	/**
	 *	Get the required boolean attribute HasHistory.
	 *
	 *	@return	The required boolean attribute HasHistory.
	 */
	boolean getRequiredHasHistory();

	/**
	 *	Get the required boolean attribute HasAuditColumns.
	 *
	 *	@return	The required boolean attribute HasAuditColumns.
	 */
	boolean getRequiredHasAuditColumns();

	/**
	 *	Get the required boolean attribute IsMutable.
	 *
	 *	@return	The required boolean attribute IsMutable.
	 */
	boolean getRequiredIsMutable();

	/**
	 *	Get the required boolean attribute IsServerOnly.
	 *
	 *	@return	The required boolean attribute IsServerOnly.
	 */
	boolean getRequiredIsServerOnly();

	/**
	 *	Get the required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour.
	 *
	 *	@return	The required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour.
	 */
	ICFBamSchema.LoaderBehaviourEnum getRequiredLoaderBehaviour();

	/**
	 *	Get the required ICFBamSchema.SecScopeEnum attribute SecScope.
	 *
	 *	@return	The required ICFBamSchema.SecScopeEnum attribute SecScope.
	 */
	ICFBamSchema.SecScopeEnum getRequiredSecScope();

	List<ICFBamRelationObj> getOnlyOwnerRelations();
	List<ICFBamRelationObj> getContainerOwnerRelations();
	ICFBamRelationObj getContainerRelation();
	ICFBamRelationObj getInheritedContainerRelation();
	ICFBamRelationObj getOwnerRelation();
	ICFBamRelationObj getInheritedOwnerRelation();
	List<ICFBamRelationObj> getOwnerLookupRelations();
	List<ICFBamRelationObj> getOwnerContainerNamedLookupChainRelations();
	List<ICFBamAtomObj> getChildrenAtoms();
	ICFBamRelationObj getSuperClassRelation();
	List<ICFBamRelationObj> getSubClassRelations();
	ICFBamIndexObj getPrimaryKeyIndex();
	List<ICFBamRelationObj> getFactoryOwnerRelations();
	List<ICFBamIndexObj> getInheritedIndexes();
	List<ICFBamIndexObj> getChildrenIndexes();
	List<ICFBamRelationObj> getInheritedRelations();
	List<ICFBamRelationObj> getChildrenRelations();

}
