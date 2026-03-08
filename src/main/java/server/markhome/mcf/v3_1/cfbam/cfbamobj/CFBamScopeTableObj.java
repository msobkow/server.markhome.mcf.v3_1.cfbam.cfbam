// Description: Java 25 Table Object implementation for Scope.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
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

public class CFBamScopeTableObj
	implements ICFBamScopeTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamScope.CLASS_CODE;
	protected static final int backingClassCode = ICFBamScope.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamScopeObj> members;
	private Map<CFLibDbKeyHash256, ICFBamScopeObj> allScope;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamScopeObj > > indexByTenantIdx;
	public static String TABLE_NAME = "Scope";
	public static String TABLE_DBNAME = "scopedef";

	public CFBamScopeTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamScopeObj>();
		allScope = null;
		indexByTenantIdx = null;
	}

	public CFBamScopeTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamScopeObj>();
		allScope = null;
		indexByTenantIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamScopeTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamScopeTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allScope = null;
		indexByTenantIdx = null;
		List<ICFBamScopeObj> toForget = new LinkedList<ICFBamScopeObj>();
		ICFBamScopeObj cur = null;
		Iterator<ICFBamScopeObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamScopeObj.
	 */
	@Override
	public ICFBamScopeObj newInstance() {
		ICFBamScopeObj inst = new CFBamScopeObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamScopeObj.
	 */
	@Override
	public ICFBamScopeEditObj newEditInstance( ICFBamScopeObj orig ) {
		ICFBamScopeEditObj edit = new CFBamScopeEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamScopeObj constructByClassCode( int backingClassCode ) {
		ICFBamScopeObj obj = null;
		if( backingClassCode == ICFBamScope.CLASS_CODE) {
			obj = ((ICFBamSchemaObj)schema).getScopeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamSchemaDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getSchemaDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamSchemaRef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getSchemaRefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamServerMethod.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getServerMethodTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamServerObjFunc.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getServerObjFuncTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamServerProc.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getServerProcTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamServerListFunc.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getServerListFuncTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTable.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTableTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamClearDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getClearDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamClearSubDep1.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getClearSubDep1TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamClearSubDep2.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getClearSubDep2TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamClearSubDep3.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getClearSubDep3TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamClearTopDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getClearTopDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDelDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDelDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDelSubDep1.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDelSubDep1TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDelSubDep2.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDelSubDep2TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDelSubDep3.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDelSubDep3TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDelTopDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDelTopDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamIndex.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getIndexTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamPopDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getPopDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamPopSubDep1.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getPopSubDep1TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamPopSubDep2.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getPopSubDep2TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamPopSubDep3.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getPopSubDep3TableObj().newInstance();
		}
		else if( backingClassCode == ICFBamPopTopDep.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getPopTopDepTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamRelation.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getRelationTableObj().newInstance();
		}
		return( obj );
	}

	@Override
	public ICFBamScopeObj realiseScope( ICFBamScopeObj Obj ) {
		ICFBamScopeObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamScopeObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamScopeObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamScopeObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.remove( keepObj.getPKey() );
					if( mapTenantIdx.size() <= 0 ) {
						indexByTenantIdx.remove( keyTenantIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamScopeObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allScope != null ) {
				allScope.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allScope != null ) {
				allScope.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamScopeObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamScopeObj createScope( ICFBamScopeObj Obj ) {
		ICFBamScopeObj obj = Obj;
		ICFBamScope rec = obj.getScopeRec();
		schema.getCFBamBackingStore().getTableScope().createScope(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamScopeObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamScopeObj readScope( CFLibDbKeyHash256 pkey ) {
		return( readScope( pkey, false ) );
	}

	@Override
	public ICFBamScopeObj readScope( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamScopeObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamScope readRec = schema.getCFBamBackingStore().getTableScope().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamScopeObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamScopeObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamScopeObj readCachedScope( CFLibDbKeyHash256 pkey ) {
		ICFBamScopeObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeScope( ICFBamScopeObj obj )
	{
		final String S_ProcName = "CFBamScopeTableObj.reallyDeepDisposeScope() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamScopeObj existing = readCachedScope( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamScopeByTenantIdxKey keyTenantIdx = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		keyTenantIdx.setRequiredTenantId( existing.getRequiredTenantId() );



		if( indexByTenantIdx != null ) {
			if( indexByTenantIdx.containsKey( keyTenantIdx ) ) {
				indexByTenantIdx.get( keyTenantIdx ).remove( pkey );
				if( indexByTenantIdx.get( keyTenantIdx ).size() <= 0 ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeScope( CFLibDbKeyHash256 pkey ) {
		ICFBamScopeObj obj = readCachedScope( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamScopeObj lockScope( CFLibDbKeyHash256 pkey ) {
		ICFBamScopeObj locked = null;
		ICFBamScope lockRec = schema.getCFBamBackingStore().getTableScope().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamScopeObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamScopeObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockScope", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamScopeObj> readAllScope() {
		return( readAllScope( false ) );
	}

	@Override
	public List<ICFBamScopeObj> readAllScope( boolean forceRead ) {
		final String S_ProcName = "readAllScope";
		if( ( allScope == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamScopeObj> map = new HashMap<CFLibDbKeyHash256,ICFBamScopeObj>();
			allScope = map;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readAllDerived( null );
			ICFBamScope rec;
			ICFBamScopeObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamScopeObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamScopeObj realised = (ICFBamScopeObj)obj.realise();
			}
		}
		int len = allScope.size();
		ICFBamScopeObj arr[] = new ICFBamScopeObj[len];
		Iterator<ICFBamScopeObj> valIter = allScope.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamScopeObj> arrayList = new ArrayList<ICFBamScopeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamScopeObj> cmp = new Comparator<ICFBamScopeObj>() {
			@Override
			public int compare( ICFBamScopeObj lhs, ICFBamScopeObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamScopeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamScopeObj> readCachedAllScope() {
		final String S_ProcName = "readCachedAllScope";
		ArrayList<ICFBamScopeObj> arrayList = new ArrayList<ICFBamScopeObj>();
		if( allScope != null ) {
			int len = allScope.size();
			ICFBamScopeObj arr[] = new ICFBamScopeObj[len];
			Iterator<ICFBamScopeObj> valIter = allScope.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamScopeObj> cmp = new Comparator<ICFBamScopeObj>() {
			public int compare( ICFBamScopeObj lhs, ICFBamScopeObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamScopeObj readScopeByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readScopeByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamScopeObj readScopeByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamScopeObj obj = readScope( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamScopeObj> readScopeByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readScopeByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamScopeObj> readScopeByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readScopeByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamScopeObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamScopeObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamScopeObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamScopeObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamScopeObj realised = (ICFBamScopeObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamScopeObj arr[] = new ICFBamScopeObj[len];
		Iterator<ICFBamScopeObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamScopeObj> arrayList = new ArrayList<ICFBamScopeObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamScopeObj> cmp = new Comparator<ICFBamScopeObj>() {
			@Override
			public int compare( ICFBamScopeObj lhs, ICFBamScopeObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamScopeObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamScopeObj readCachedScopeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamScopeObj obj = null;
		obj = readCachedScope( Id );
		return( obj );
	}

	@Override
	public List<ICFBamScopeObj> readCachedScopeByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedScopeByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamScopeObj> arrayList = new ArrayList<ICFBamScopeObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamScopeObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamScopeObj arr[] = new ICFBamScopeObj[len];
				Iterator<ICFBamScopeObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamScopeObj obj;
			Iterator<ICFBamScopeObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamScopeObj> cmp = new Comparator<ICFBamScopeObj>() {
			@Override
			public int compare( ICFBamScopeObj lhs, ICFBamScopeObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeScopeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamScopeObj obj = readCachedScopeByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeScopeByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeScopeByTenantIdx";
		ICFBamScopeObj obj;
		List<ICFBamScopeObj> arrayList = readCachedScopeByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamScopeObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamScopeObj updateScope( ICFBamScopeObj Obj ) {
		ICFBamScopeObj obj = Obj;
		schema.getCFBamBackingStore().getTableScope().updateScope( null,
			Obj.getScopeRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getScopeTableObj().getClassCode() ) {
			obj = (ICFBamScopeObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteScope( ICFBamScopeObj Obj ) {
		ICFBamScopeObj obj = Obj;
		schema.getCFBamBackingStore().getTableScope().deleteScope( null,
			obj.getScopeRec() );
		Obj.forget();
	}

	@Override
	public void deleteScopeByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamScopeObj obj = readScope(Id);
		if( obj != null ) {
			ICFBamScopeEditObj editObj = (ICFBamScopeEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamScopeEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeScopeByIdIdx( Id );
	}

	@Override
	public void deleteScopeByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamScopeObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamScopeObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableScope().deleteScopeByTenantIdx( null,
				TenantId );
			Iterator<ICFBamScopeObj> iter = dict.values().iterator();
			ICFBamScopeObj obj;
			List<ICFBamScopeObj> toForget = new LinkedList<ICFBamScopeObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableScope().deleteScopeByTenantIdx( null,
				TenantId );
		}
		deepDisposeScopeByTenantIdx( TenantId );
	}
}